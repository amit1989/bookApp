    package bookapp

import grails.converters.JSON
import org.json.simple.JSONArray
import org.json.simple.JSONObject

import java.text.DateFormat
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
import javax.sql.DataSource

class BookController {
    def BookHelperService bookHelperService;
    def PushNotificationService ;

    JSONObject jsonObject
    List jsonErrors
    Boolean jsonStatus
    List jsonResponse
    List jsonRequest

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
                String query = "from Book as b where b.isCompleted=:flagComplete order by b.dateCreated desc"
                def offset = Integer.parseInt( params.offset  )* 10;
                book = Book.findAll(query,[flagComplete: false],[ max: 10, offset: offset]);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }else {
                String query = "from Book as b where b.isCompleted=:flagComplete order by b.dateCreated desc"
                book = Book.findAll(query, [flagComplete: false]);
                jsonMap = bookHelperService.getBookHasMap(book)
                render jsonMap as JSON
            }
        }catch (Exception e){
            println "error occured: " + e.getMessage()
        }
    }

    def fetchBooksByCity(){
        println('in fetchLatestBooks')
        HashMap jsonMap = new HashMap()
        try {

            def books = PickupLocation.findAllByCity(params.city);
            def books1 = [];
            books.each {
                books1.push( Book.findById(it.bookId) )
            }
            render books1 as JSON

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
                def offset = Integer.parseInt( params.offset  )* 10;
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

                responseObj.put("pickupId", address.id );
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
            responseObj.put("mobile", addressOfBook.mobileNumber);


            obj.put("status", "success")
            obj.put("address", responseObj)
            render  obj as JSON;

        }catch (Exception e){
            obj.put("status", "failed")
            obj.put("error", e.getMessage())
            render  obj as JSON;
        }
    }

    //http://localhost:8080/bookApp/book/addToWishList?token=1444739497970&bookRef=23
    @Transactional
    def addToWishList(){

        JSONObject responseObj = new JSONObject();
        JSONObject obj = new JSONObject()

        try {
            UserTable user = Book.getUserIDByToken(params.token);

            WishList address = new WishList()
            address.user = user;
            address.bookRef = params.bookRef;

            if(address.save(flush: true, failOnError: true)){
                responseObj.put("message", "Added To Wishlist");
                obj.put("status", "success")
                obj.put("response", responseObj)
                render obj as JSON
            }

        }catch (Exception e){
            responseObj.put("message", "Error");
            obj.put("status", "failed")
            obj.put("response", e.getMessage())
            render obj as JSON
        }
    }

    @Transactional
    def removeWishList(){
        JSONObject responseObj = new JSONObject();
        JSONObject obj = new JSONObject()

        try {
            UserTable user = Book.getUserIDByToken(params.token);

            def wishObject = WishList.findByUserAndBookRef(user, params.bookRef)

            println wishObject

            wishObject.delete(flush: true,  failOnError: true)

            responseObj.put("message", "Removed From Wishlist");
            obj.put("status", "success")
            obj.put("response", responseObj)
            render obj as JSON

        }catch (Exception e){
            responseObj.put("message", "Error");
            obj.put("status", "failed")
            obj.put("response", e.getMessage())
            render obj as JSON
        }

    }

    /*
     * url to access
     * http://localhost:8080/bookApp/book/getUserWishList?token=1444739497970
     */
    def getUserWishList(){
        initiateJSONParameters()
        validateUserWishListParams()
        def books
        if(jsonErrors?.size() == 0){
            def user = UserTable.findByUserToken(params.token)
            if(!user){
                jsonErrors.push("There is no user associated with the provided token")
            }else{
                def wishListInstance = WishList.findAllByUser(user)
                if(wishListInstance && wishListInstance?.size() > 0){
                    def bookList = []
                    for(int i = 0; i< wishListInstance.size(); i++){
                        String book = wishListInstance.get(i).getBookRef();
                        Book bookInstance = Book.findById(book);
                        if(bookInstance) {
                            bookList.add(bookInstance)
                        }
                    }

                    books = bookHelperService.getBooksList(bookList)
                    jsonStatus = true
                }else{
                    jsonErrors.push("no wishlist found")
                }
            }
        }

        jsonObject.put("response", books)

        render jsonObject as JSON
    }//end of getUserWishtList

    def validateUserWishListParams(){
        if(!params.token){
            jsonErrors.push("token not found")
        }
    }

    //will get bookId, UserId, As Params
    @Transactional
    def generateShareRequest(){

        JSONObject responseObj = new JSONObject();
        JSONObject obj = new JSONObject()

        try {

            Book bookId = Book.findById(params.bookId)
            UserTable user = Book.getUserIDByToken(params.token);

            Request request = new Request()
            request.is_completed = false;
            request.book = bookId;
            request.user = user;
            request.requestToken = bookHelperService.generateRequestToken();


            if(request.save(flush: true,  failOnError: true)){

                if(bookId.shareCount == null){
                    bookId.shareCount = 1;
                }else{
                    bookId.shareCount = bookId.getShareCount() + 1;
                }
                bookId.save( flush: true, failOnError: true)

                sendPushNotification(user.gcm, "requestToken", request.requestToken)
                sendPushNotification(bookId.user.gcm, "notifySeller", ""+user.userName)

                responseObj.put("message", "Request Send to user");
                obj.put("status", "success")
                obj.put("requestToken", request.requestToken)
                obj.put("response", responseObj)
                render obj as JSON
            }


        }catch (Exception e){
            responseObj.put("message", "Fail to send request"+e.getMessage());
            obj.put("status", "fail")
            obj.put("response", responseObj)
            render obj as JSON
        }
    }

    @Transactional
    def confirmBookRequest(){
        print 'params' + params
        JSONObject responseObj = new JSONObject();
        JSONObject obj = new JSONObject()

        try{
            def book = Book.findById(params.bookId)
            def request = null;
            if(book != null){
                request = Request.findByBookAndRequestToken(book, params.requestToken)
                println '-----*'+request;

                request.is_completed = true;
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();

                request.sharedDate = dateFormat.format(date)
                request.save(flush: true, failOnError: true)

                book.isCompleted = true;
                book.save(flush: true, failOnError: true)
            }

            if(request == null){
                responseObj.put("message", "doesnt match bookId or token ");
                obj.put("status", "failed")
                obj.put("response", responseObj)
                render obj as JSON
            }else{
                sendPushNotification(request.user.gcm, "confirmBookRequest", ""+book.user.name)
                responseObj.put("message", "confirmed");
                obj.put("status", "success")
                obj.put("userDetail", request.user.userToken)
                obj.put("response", responseObj)
                render obj as JSON
            }

        }catch (Exception e){
            responseObj.put("message", "doesnt match bookId or token ");
            obj.put("status", "failed")
            obj.put("response", responseObj)
            render obj as JSON
        }
    }


    def sharedBooksRequest(){

        JSONObject booksobject = new JSONObject()
        JSONArray array = new JSONArray()

        def rec =  Book.executeQuery("select b.id, b.title, r.is_completed, r.requestToken, r.user.id, b.endDate, r.sharedDate, r.user.name from Book b ,Request r where b.id = r.book.id and b.id = :bookId", [bookId : Long.parseLong(params.bookId)]);

        println rec
        for(int i=0; i < rec.size(); i++ ){
            HashMap jsonMap = new HashMap()

            jsonMap.put("bookId", rec[i][0] )
            jsonMap.put("title", rec[i][1] )
            jsonMap.put("is_completed", rec[i][2] )
            jsonMap.put("requestToken", rec[i][3] )
            jsonMap.put("userId", rec[i][4] )
            jsonMap.put("SharedDays", rec[i][5] )
            jsonMap.put("sharedOnDate", rec[i][6] )
            jsonMap.put("name", rec[i][7] )
            array.putAt( i, jsonMap )
        }
        booksobject.put("books", array)
        render booksobject as JSON
    }

    def getMySharedTokens(){
        JSONObject booksobject = new JSONObject()
        JSONArray array = new JSONArray()

        UserTable user  = Book.getUserIDByToken(params.token);

        def rec =  Book.executeQuery("select b.id, b.title, r.is_completed, r.requestToken, r.user.id, b.endDate, r.sharedDate from Book b ,Request r where b.id = r.book.id and r.user.id = :bookId", [bookId : user.id]);

        println rec
        for(int i=0; i < rec.size(); i++ ){
            HashMap jsonMap = new HashMap()

            jsonMap.put("bookId", rec[i][0] )
            jsonMap.put("title", rec[i][1] )
            jsonMap.put("is_completed", rec[i][2] )
            jsonMap.put("requestToken", rec[i][3] )
            jsonMap.put("userId", rec[i][4] )
            jsonMap.put("SharedDays", rec[i][5] )
            jsonMap.put("sharedOnDate", rec[i][6] )
            array.putAt( i, jsonMap )
        }
        booksobject.put("books", array)
        render booksobject as JSON
    }

    def getCategories(){
        def category = Category.findAll()
        render category as JSON
    }

    def getUserDetailByIdOrToken(){
        if(params.token){
         def user  = Book.getUserIDByToken(params.token)
            render user as JSON
        }else if(params.userId){
            def user  = UserTable.findById(params.userId)
            render user as JSON
        }
    }


    def sendPushNotification(String gcm, String requestType, String details){
        bookHelperService.prepareAndroidNotification(gcm, requestType, details)
    }

    @Transactional
    def removeUserBook(){
        initiateJSONParameters()
        try{
            if(params.bookId) {
                def book = Book.findById(params.bookId)
                if(book) {
                    Tags.findAllByBook(book).each { it.delete(flush: true, failOnError: true) }
                    PickupLocation.findAllByBook(book).each { it.delete(flush: true, failOnError: true) }
                    Request.findAllByBook(book).each { it.delete(flush: true, failOnError: true) }

                    book.delete(flush: true, failOnError: true)
                    jsonResponse.push("book deleted successfully")
                    jsonStatus = true
                }else{
                    jsonErrors.push("There is no book associated with the provided bookId")
                }
            }else{
                jsonErrors.push("bookId cannot be empty")
            }

        }catch (Exception e){
            jsonErrors.push("Error occured while deleting. Details: " + e.getMessage())
        }
        renderResponse()
    }

    def getLatestBooks(){
        String query = "from PickupLocation location WHERE location.city=:city and location.book.isCompleted=false ORDER BY location.book.dateCreated desc"
        initiateJSONParameters()
        def bookList

        if(params.city){
            def locations
            locations = PickupLocation.findAll(query, [city:params.city], [max:6])
            bookList = locations?.book
        }else{
                bookList = Book.findAllByIsCompleted(false, [max:6, order: 'desc', sort:'dateCreated'])
        }

        if(jsonErrors.size() == 0){
            jsonStatus = true
            jsonResponse.push(bookHelperService.getBookHasMap(bookList))
        }

        renderResponse()
    }

    def retriveBookTag(){
        initiateJSONParameters()

        if(params.bookId) {
            def book = Book.findById(params.bookId);
            if (book) {
                def tags = Tags.findAllByBook(book)
                if(tags && tags?.size() > 0) {
                    def tagList = [];
                    tags.each {
                        JSONObject jsonObject1 = new JSONObject()
                        jsonObject1.put("tags", it.tags)
                        tagList.push(jsonObject1)
                        jsonStatus = true
                    }
                    jsonResponse.push(tagList)
                }else{
                    jsonErrors.push("No tags found")
                }
            } else {
                jsonErrors.push("No book found with the provided book id")
            }
        }else{
            jsonErrors.push("book id not found")
        }

        renderResponse()
    }

    def searchByTag(){
        JSONObject booksobject = new JSONObject()
        Map array = new HashMap();
        String query
        def tagList
        params.sort = "tag.book.dateCreated"
        params.order = "desc"
        if(params.city){
            query = "from Tags tag where tag.location.city=:city"
            if(params.offset){
                def offset = Integer.parseInt( params.offset  )* 10;
                tagList = Tags.findAll(query, [city: params.city], [sort:params.sort, max:10, offset:offset])
            }else{
                tagList = Tags.findAll(query, [city: params.city], [sort:params.sort])
            }
        }
        if(params.tags){
            if(query){
                query += " and tag.tags=:tags"
                if(params.offset) {
                    def offset = Integer.parseInt(params.offset) * 10;
                    tagList = Tags.findAll(query, [city: params.city, tags: params.tags],[max:10, offset:offset])
                }else{
                    tagList = Tags.findAll(query, [city: params.city, tags: params.tags])
                }
            }else{
                query += "from Tags tag where tag.tags=:tags"
                if(params.offset) {
                    def offset = Integer.parseInt(params.offset) * 10;
                    tagList = Tags.findAll(query, [tags: params.tags],[max:10, offset:offset])
                }else{
                    tagList = Tags.findAll(query, [tags: params.tags])
                }
            }
        }
        if(!params.city && !params.tags){
            if(params.offset) {
                def offset = Integer.parseInt(params.offset) * 10;
                tagList = Tags.list([offset:offset, max:10])
            }else{
                tagList = Tags.list()
            }
        }

        tagList.each {
            def book = it?.book
            if(book){
                if(!book.isCompleted) {
                    array.put(it?.book?.id, bookHelperService.getBookAsJson(it?.book))
                }
            }
        }

        def books = []
        array.each {
            books.push(it?.getValue())
        }
        booksobject.put("books", books.sort{a,b->a.dateCreated <=> b.dateCreated})
        render booksobject  as JSON
    }

    def getBookListByCityAndCat() {
        initiateJSONParameters()

        def book;
        def bookList = [];

        if(params.category) {
            Category category = Category.findByName(params.category)
            HashMap jsonMap = new HashMap()
            if (params.offset) {
                def offset = Integer.parseInt(params.offset) * 10;
                def books = PickupLocation.findAllByCity(params.city, [max: 10, offset: offset])

                books.each {
                    book = Book.findByIdAndIsCompletedAndCategory(it.book.id, false, category)
                    bookList.push(book)
                }
            } else {
                def books = PickupLocation.findAllByCity(params.city)
                books.each {
                    book = Book.findByIdAndIsCompletedAndCategory(it.book.id, false, category)
                    bookList.push(book)
                }
            }
        }else{
            if (params.offset) {
                def offset = Integer.parseInt(params.offset) * 10;
                def locations = PickupLocation.list(max:10, offset: offset)
                locations.each {
                    book = Book.findByIdAndIsCompletedAndCategory(it.book.id, false, category)
                    bookList.push(book)
                }
            }else{
                def locations = PickupLocation.list()
                locations.each {
                    book = Book.findByIdAndIsCompletedAndCategory(it.book.id, false, category)
                    bookList.push(book)
                }
            }
        }

        def books = bookHelperService.getBookHasMap(bookList)
        jsonObject.put("books", books)
        jsonResponse.push(jsonObject)

        renderResponse()
    }

    /*
     * url to access:
     * http://localhost:8080/bookApp/book/getBookListByCity
     *              (or)
     * http://localhost:8080/bookApp/book/getBookListByCity?offset=0/1/etc...
     *              (or)
     * http://localhost:8080/bookApp/book/getBookListByCity?city=pune/etc...
     *              (or)
     * http://localhost:8080/bookApp/book/getBookListByCity?city=pune?offset=0/1/etc...
     */
    def getBookListByCity() {
        initiateJSONParameters()

        def bookList = new HashMap()
        def locations

        if(params.city){
            if(params.offset) {
                def offset = Integer.parseInt(params.offset) * 10;
                locations = PickupLocation.findAllByCity(params.city, [max: 10, offset: offset])
            }else{
                locations = PickupLocation.findAllByCity(params.city)
            }
        }else{
            if(params.offset) {
                def offset = Integer.parseInt( params.offset  )* 10;
                locations = PickupLocation.list([max: 10, offset: offset])
            }else{
                locations = PickupLocation.list()
            }
        }

        locations.each {
            bookList.put(it?.book?.id, it.book)
        }

        def books = bookHelperService.getBookHasMap(bookList.values())

        if(jsonErrors.size() == 0) {
            jsonStatus = true
            jsonResponse.push(books)
        }

        renderResponse()
    }//end of getBookListByCity


    /*
     * url to access:
     * http://localhost:8080/bookApp/book/getCityTags?city=pune/etc...
     *              (or)
     * http://localhost:8080/bookApp/book/getCityTags?city=pune?offset=0/1/etc...
     */
    def getCityTags(){

        Map hashMap = new HashMap()
        initiateJSONParameters()
        validateCityTagsFields()

        if(jsonErrors?.size() == 0){

            def locations = PickupLocation.findAllByCity(params.city)
            def tagList

            if(params.offset) {
                def offset = Integer.parseInt(params.offset) * 10;
                tagList = Tags.findAllByLocationInList(locations, [ max: 10, offset: offset])
            }else{
                tagList = Tags.findAllByLocationInList(locations)
            }

            tagList.each{
                hashMap.put(it?.tags, it?.tags?:"")
            }

            hashMap.each {
                JSONObject obj = new JSONObject()
                obj.put("tag", it?.value ?: "")
                jsonResponse.push(obj)
            }

            if(hashMap.size() > 0) {
                jsonStatus = true
            }else{
                jsonErrors.push("No tags found")
                jsonStatus = false
            }
        }

        renderResponse()
    }//end of getCityTags


    //validate parameters to get city tags
    def validateCityTagsFields(){
        if(!params.city){
            jsonErrors.push("Please provide city")
        }
    }//end of validateCityTagsFields

    /*
     * url to access:
     * http://localhost:8080/bookApp/book/filterBook
     */
    def filterBook(){
        initiateJSONParameters()
        def books = getBookList()
        def bookList

        if(books) {
            bookList = bookHelperService.getBookHasMap(books)
        }else{
            jsonErrors.push("No books found")
        }

        if(jsonErrors.size() == 0){
            jsonStatus = true
            jsonResponse.push(bookList)
        }

        renderResponse()
    }//end of filterBook

    //get list of all books by category, city, shared, onsell, donated
    def getBookList(){
        boolean isParamsFound = false
        def bookList


        if(params.category){
            isParamsFound = true
            def categories = Category.findAllByName(params.category)
            if(categories){
                bookList = Book.findAllByCategoryInList(categories)
            }
        }

        if(params.city){
            isParamsFound = true
            def locations = PickupLocation.findAllByCity(params.city)
            if(locations){
                if(params.category) {
                    def categories = Category.findAllByName(params.category)
                    println "categories: " +categories
                    bookList = Book.findAllByCategoryInListAndIdInList(categories, locations?.book?.id)
                }else if(params.searchSting){
                    bookList = Book.findAllByTitleIlikeAndIdInList("%"+params.searchSting+"%", locations?.book?.id );

                } else {
                    bookList = Book.findAllByIdInList(locations?.book?.id)
                }
            }else{
                bookList = []
            }
        }

        if( params.shared){
            isParamsFound = true
            if(params.category || params.city) {
                bookList = Book.findAllByIdInListAndIsCompletedAndIsShared(bookList?.id, false, true);
            }else{
                bookList = Book.findAllByIsCompletedAndIsShared(false, true);
            }
        }

        if( params.onsell){
            isParamsFound = true
            if(params.category || params.city) {
                bookList = Book.findAllByIdInListAndIsCompletedAndIsOnSell(bookList?.id, false, true);
            }else{
                bookList = Book.findAllByIsCompletedAndIsOnSell(false, true);
            }
        }

        if( params.donated){
            isParamsFound = true
            if(params.category || params.city) {
                bookList = Book.findAllByIdInListAndIsCompletedAndIsDonated(bookList?.id, false, true);
            }else{
                bookList = Book.findAllByIsCompletedAndIsDonated(false, true);
            }
        }

        if(!isParamsFound){
            bookList = Book.list()
        }

        if(params.offset) {
            def offset = Integer.parseInt( params.offset  )* 10;
            return Book.findAllByIdInListAndIsCompleted(bookList?.id, false, [ max: 10, offset: offset, order:'desc', sort:'dateCreated'])
        }else{
            return Book.findAllByIdInListAndIsCompleted(bookList?.id, false, [order:'desc', sort: 'dateCreated'])
        }

        return bookList
    }//end of getBookList

    /* url to access
     * http://localhost:8080/bookApp/book/create_book?title=History&token=12125244
     */
    def validateBookFields(){
        if(!params.title){
            jsonErrors.push("book title cannot be empty")
        }
        if(params.token) {
            def user = UserTable.findByUserToken(params.token)
            if(user){
                params.user = user
            }else{
                jsonErrors.push("No user exist with the provided token")
            }
        }else{
            jsonErrors.push("token cannot be empty")
        }

        if(params.categoryId){
            params.category = Category.findById(params.categoryId)
        }
    }

    @Transactional
    def create_book(){
        initiateJSONParameters()
        validateBookFields()
        if(jsonErrors.size() == 0){
            jsonStatus = true
            Book book = new Book(params)
            UserTable user = params.user
            if(book.save(flush:true)){
                if (params.image && request.getFile('image')) {
                    try {
                        def uploadedFile = request.getFile('image')
                        def webRootDir = servletContext.getRealPath("/")
                        def userDir = new File(webRootDir, "/images/books")
                        userDir.mkdirs()
                        String fileName = Book.getTimeStamp() + user.userToken + ".jpg"
                        uploadedFile.transferTo(new File(userDir, fileName))
                        book.imageUrl = fileName
                        book.save(flush:true)
                    }catch(Exception e){
                        jsonErrors.push("Error occured while saving book image" + e.printStackTrace())
                    }
                }
                jsonStatus = true
                Map bookMap = new HashMap()
                bookMap.put("bookId", book.getId())
                jsonResponse.push(bookMap)
            }
        }
        renderResponse()
    }//end of create_book

    /*
     * url to access
     * http://localhost:8080/bookApp/book/addCustomTag?bookId=8&tags=kdk&pickupId=
     */
    @Transactional
    def addCustomTag(){
        initiateJSONParameters()
        addCustomTagFields()

        if(jsonErrors?.size() == 0){
            if(params.tags){
                def tagList = params.tags.toString().split(",")

                tagList.each {
                    Tags tags = new Tags()
                    tags.book = params.book
                    tags.location = params.location
                    tags.tags = it
                    tags.save(flus:true, failOnError: true)
                }

                jsonStatus = true
                jsonResponse.push("tag(s) added")
            }
        }

        renderResponse()
    }//end of addCustomTag

    def addCustomTagFields(){
        def book
        def location

        if(!params.bookId){
            jsonErrors.push("Book id is required")
            return
        }else{
            book = Book.get(params.bookId)
            if(!book){
                jsonErrors.push("No book is associated with the given book id")
                return
            }
            params.book = book
        }

        if(params.pickupId){
            location = PickupLocation.get(params.pickupId)
            if(!location){
                jsonErrors.push("No location is associated with the given pickup location id")
                return
            }
            params.location = location
        }
    }

    def contactUs (){

        initiateJSONParameters()
        ContactUs contactUs = new ContactUs(params)

        try {
            if(contactUs.save(flush:true, failOnError: true)) {
                jsonResponse.push("Added")
                jsonStatus = true
            }

        }catch (Exception e){
            jsonErrors.push("Failed")
        }
        renderResponse()
    }

    private void initiateJSONParameters(){
        jsonObject = new JSONObject()
        jsonErrors = new ArrayList()
        jsonStatus = new ArrayList()
        jsonResponse = new ArrayList()
        jsonRequest = new ArrayList()
    }

    def renderResponse(){
        jsonObject.put("status", jsonStatus)
        jsonObject.put("errorList", jsonErrors)
        jsonObject.put("response", jsonResponse)
        render jsonObject as JSON
    }


    @Transactional
    def edit_book(){
        initiateJSONParameters()
        validateBookFields()
        if(jsonErrors.size() == 0){
            jsonStatus = true
            Book book = Book.findById(params.bookId)
            if(params.title){
                book.title = params.title
            }
            if(params.isbn){
                book.isbn = params.isbn
            }
            if(params.discreption){
                book.discreption = params.discreption
            }
            if(params.bookEdition){
                book.bookEdition = params.bookEdition
            }
            if(params.yearOfBook){
                book.yearOfBook = params.yearOfBook
            }
            if(params.author){
                book.author = params.author
            }
            if(params.categoryId){
                book.category = params.categoryId
            }
            if (params.discount){
                book.discount = params.discount
            }
            if (params.originalCost){
                book.originalCost = params.originalCost
            }
            if(params.endDate){
                book.endDate = params.endDate
            }
            UserTable user = params.user
            if(book.save(flush:true)){
                if (params.image && request.getFile('image')) {
                    try {
                        def uploadedFile = request.getFile('image')
                        def webRootDir = servletContext.getRealPath("/")
                        def userDir = new File(webRootDir, "/images/books")
                        userDir.mkdirs()
                        String fileName = Book.getTimeStamp() + user.userToken + ".jpg"
                        uploadedFile.transferTo(new File(userDir, fileName))
                        book.imageUrl = fileName
                        book.save(flush:true)
                    }catch(Exception e){
                        jsonErrors.push("Error occured while saving book image" + e.printStackTrace())
                    }
                }
                jsonStatus = true
                Map bookMap = new HashMap()
                bookMap.put("bookId", book.getId())
                jsonResponse.push(bookMap)
            }
        }
        renderResponse()
    }//end


    @Transactional
    def edit_book_address(){

        JSONObject obj = new JSONObject();
        JSONObject responseObj = new JSONObject();
        try{
            def bookId = Book.findById(params.bookId)
            PickupLocation address = PickupLocation.findByBook(bookId)


            if(params.addressOne){
                address.addressOne = params.addressOne
            }else if(params.addressTwo){
                address.addressTwo = params.addressTwo
            }else if(params.city){
                address.city = params.city
            }else if(params.latitude){
                address.latitude = params.latitude
            }else if (params.longitude){
                address.longitude = params.longitude
            }else if (params.mobileNumber){
                address.mobileNumber = params.mobileNumber
            }

            if(address.save( flush:true, failOnError: true )){

                responseObj.put("pickupId", address.id );
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
}