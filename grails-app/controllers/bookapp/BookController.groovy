package bookapp

import grails.converters.JSON
import org.json.simple.JSONArray
import org.json.simple.JSONObject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BookController {

    def BookHelperService bookHelperService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Book.list(params), model:[bookInstanceCount: Book.count()]
    }

    def show(Book bookInstance) {
        respond bookInstance
    }

    def create() {
        respond new Book(params)
    }

    @Transactional
    def save(Book bookInstance) {
        if (bookInstance == null) {
            notFound()
            return
        }

        if (bookInstance.hasErrors()) {
            respond bookInstance.errors, view:'create'
            return
        }

        bookInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bookInstance.label', default: 'Book'), bookInstance.id])
                redirect bookInstance
            }
            '*' { respond bookInstance, [status: CREATED] }
        }
    }

    def edit(Book bookInstance) {
        respond bookInstance
    }

    @Transactional
    def update(Book bookInstance) {
        if (bookInstance == null) {
            notFound()
            return
        }

        if (bookInstance.hasErrors()) {
            respond bookInstance.errors, view:'edit'
            return
        }

        bookInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Book.label', default: 'Book'), bookInstance.id])
                redirect bookInstance
            }
            '*'{ respond bookInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Book bookInstance) {

        if (bookInstance == null) {
            notFound()
            return
        }

        bookInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Book.label', default: 'Book'), bookInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookInstance.label', default: 'Book'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    //http://localhost:8080/bookApp/book/create_book?title=History&token=12125244
    @Transactional
    def create_book(){

        println(params)

        JSONObject obj = new JSONObject();
        JSONObject responseObj = new JSONObject();
        try{
            def user = Book.getUserIDByToken(params.token)
            println user
            Book book = new Book(params)
            book.user=user;
            book.category = Category.findById(params.categoryId)

            if(book.save( flush:true, failOnError: true )){

                responseObj.put("message", "book created");
                obj.put("success", responseObj)
                render obj as JSON
            }

        }catch (Exception e){
            e.printStackTrace()
            responseObj.put("message", "failed to create book");
            responseObj.put("exception", e.getMessage());
            obj.put("error", responseObj)
            render obj as JSON
        }
    }

    @Transactional
    def markComplete(){

        JSONObject obj = new JSONObject();
        JSONObject responseObj = new JSONObject();

        try{

            def book = Book.findById(params.bookId)
            book.isCompleted = true;

            if(book.save(flush: true, failOnError: true)){
                responseObj.put("message", "book status complete");
                obj.put("success", responseObj)
                render obj as JSON
            }

        }catch (Exception e){
            responseObj.put("message", "failed");
            responseObj.put("exception", e.getMessage());
            obj.put("success", responseObj)
            render obj as JSON
        }
    }

    //http://localhost:8080/bookApp/book/fetchUserBook?token=12125244
    //Tested
    def fetchUserBook(){
        HashMap jsonMap = new HashMap()

        try {

            UserTable user = Book.getUserIDByToken(params.token);
            def book
            if(params.offset){
                def offset = params.offset * 10;
                book = Book.findAllByUser(user ,[max: 10, offset: offset]);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON

            }else {
                book = Book.findAllByUser(user);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON

            }

        }catch (Exception e){
            println "error occured: " + e.getMessage()

        }
    }

    //http://localhost:8080/bookApp/book/fetchUserBook?fetchLatestBooks
    //http://localhost:8080/bookApp/book/fetchUserBook?fetchLatestBooks?offset=0
    def fetchLatestBooks(){
        println('in fetchLatestBooks')
        HashMap jsonMap = new HashMap()
        try {
            def book
            if(params.offset){
                String query = "from Book as b where b.isCompleted=:flagComplete"
                def offset = params.offset * 10;
                book = Book.findAll(query,[flagComplete: false],[ max: 10, offset: offset, sort: 'created_at', order: 'desc']);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }else {
                String query = "from Book as b where b.isCompleted=:flagComplete "
                book = Book.findAll(query, [flagComplete: false]);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }
        }catch (Exception e){
            println "error occured: " + e.getMessage()
        }
    }

    def fetchLatestByCat(){

        HashMap jsonMap = new HashMap()
        try {
            def book
            Category category = Category.findByName(params.category)
            if(params.offset){
//                String query = "from Book as b where b.category=:category"
                def offset = params.offset * 10;
                book = Book.findAllByCategoryAndIsCompleted(category, false, [max: 10, offset: offset]);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }else {
//                String query = "from Book as b where b.category=:category"
                book = Book.findAllByCategoryAndIsCompleted(category, false);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }
        }catch (Exception e){
            println "error occured: " + e.getMessage()
        }
    }


    @Transactional
    def save_book_address(){

        println(params)
        JSONObject obj = new JSONObject();
        JSONObject responseObj = new JSONObject();
        try{
            def bookId = Book.findById(params.bookId)
            PickupLocation address = new PickupLocation(params)
            address.book = bookId
            address.user =  Book.getUserIDByToken(params.token)
            if(address.save( flush:true, failOnError: true )){

                responseObj.put("message", "Adress Confirmed");
                obj.put("success", responseObj)
                render obj as JSON
            }

        }catch (Exception e){
            e.printStackTrace()
            responseObj.put("message", "failed to Add Address");
            responseObj.put("exception", e.getMessage());
            obj.put("error", responseObj)
            render obj as JSON
        }

    }

    def request_book_address(){
        Book book = Book.findById(params.bookId)
        def addressOfBook = PickupLocation.findByBook(book)

        JSONObject obj = new JSONObject()


        try{

            JSONObject responseObj = new JSONObject();
            responseObj.put("addressOne", addressOfBook.addressOne);
            responseObj.put("addressTwo", addressOfBook.addressTwo);
            responseObj.put("city", addressOfBook.city);
            responseObj.put("bookId", addressOfBook.bookId);
            responseObj.put("latitude", addressOfBook.latitude);
            responseObj.put("longitude", addressOfBook.longitude);

            obj.put("status", "success")
            obj.put("address", responseObj)
            render  obj as JSON;

        }catch (Exception e){
            obj.put("status", "failed")
            obj.put("error", e.getMessage())
            render  obj as JSON;
        }
    }


}