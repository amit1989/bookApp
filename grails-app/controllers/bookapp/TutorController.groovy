package bookapp

import grails.converters.JSON
import org.json.simple.JSONObject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TutorController {
    def BookHelperService bookHelperService;

    JSONObject jsonObject
    List jsonErrors
    Boolean jsonStatus
    List jsonResponse
    List jsonRequest
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tutor.list(params), model: [tutorInstanceCount: Tutor.count()]
    }

    def show(Tutor tutorInstance) {
        respond tutorInstance
    }

    def create() {
        respond new Tutor(params)
    }

    @Transactional
    def save(Tutor tutorInstance) {
        if (tutorInstance == null) {
            notFound()
            return
        }

        if (tutorInstance.hasErrors()) {
            respond tutorInstance.errors, view: 'create'
            return
        }

        tutorInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tutorInstance.label', default: 'Tutor'), tutorInstance.id])
                redirect tutorInstance
            }
            '*' { respond tutorInstance, [status: CREATED] }
        }
    }

    def edit(Tutor tutorInstance) {
        respond tutorInstance
    }

    @Transactional
    def update(Tutor tutorInstance) {
        if (tutorInstance == null) {
            notFound()
            return
        }

        if (tutorInstance.hasErrors()) {
            respond tutorInstance.errors, view: 'edit'
            return
        }

        tutorInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tutor.label', default: 'Tutor'), tutorInstance.id])
                redirect tutorInstance
            }
            '*' { respond tutorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Tutor tutorInstance) {

        if (tutorInstance == null) {
            notFound()
            return
        }

        tutorInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tutor.label', default: 'Tutor'), tutorInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tutorInstance.label', default: 'Tutor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }


    @Transactional
    def create_tutor(){
        initiateJSONParameters()
        validateBookFields()
        if(jsonErrors.size() == 0){
            jsonStatus = true
            Tutor tutor = new Tutor(params)
            UserTable user = params.user
            if(tutor.save(flush:true)){
                if (params.image && request.getFile('image')) {
                    try {
                        def uploadedFile = request.getFile('image')
                        def webRootDir = servletContext.getRealPath("/")
                        def userDir = new File(webRootDir, "/images/tutor")
                        userDir.mkdirs()
                        String fileName = Book.getTimeStamp() + user.userToken + ".jpg"
                        uploadedFile.transferTo(new File(userDir, fileName))
                        tutor.imageUrl = fileName
                        tutor.save(flush:true)
                    }catch(Exception e){
                        jsonErrors.push("Error occured while saving record" + e.printStackTrace())
                    }
                }
                jsonStatus = true
                Map bookMap = new HashMap()
                bookMap.put("id", tutor.getId())
                jsonResponse.push(bookMap)
            }
        }
        renderResponse()
    }


    def validateBookFields(){

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
            params.category = TutorCategory.findById(params.categoryId)
        }
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

    def filterTutor(){
        initiateJSONParameters()
        def tutor = getTutorList()
        def tutorList

        if(tutor) {
            tutorList = bookHelperService.getTutorHasMap(tutor)
        }else{
            jsonErrors.push("No books found")
        }

        if(jsonErrors.size() == 0){
            jsonStatus = true
            jsonResponse.push(tutorList)
        }

        renderResponse()
    }//end of filterBook


    def getTutorList() {
        boolean isParamsFound = false
        def tutorList


        if (params.category) {
            isParamsFound = true
            def categories = TutorCategory.findAllByName(params.category)
            if (categories) {
                tutorList = Tutor.findAllByCategoryInList(categories)
            }
        }
    }
}
