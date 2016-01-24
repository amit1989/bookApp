package bookapp

import grails.converters.JSON
import org.json.simple.JSONObject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserTableController {
    def userHelperService

    JSONObject jsonObject
    List jsonErrors
    Boolean jsonStatus
    List jsonResponse
    List jsonRequest

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserTable.list(params), model: [userTableInstanceCount: UserTable.count()]
    }

    def show(UserTable userTableInstance) {
        respond userTableInstance
    }

    def create() {
        respond new UserTable(params)
    }

    @Transactional
    def save(UserTable userTableInstance) {
        if (userTableInstance == null) {
            notFound()
            return
        }

        if (userTableInstance.hasErrors()) {
            respond userTableInstance.errors, view: 'create'
            return
        }

        userTableInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userTableInstance.label', default: 'UserTable'), userTableInstance.id])
                redirect userTableInstance
            }
            '*' { respond userTableInstance, [status: CREATED] }
        }
    }

    def edit(UserTable userTableInstance) {
        respond userTableInstance
    }

    @Transactional
    def update(UserTable userTableInstance) {
        if (userTableInstance == null) {
            notFound()
            return
        }

        if (userTableInstance.hasErrors()) {
            respond userTableInstance.errors, view: 'edit'
            return
        }

        userTableInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UserTable.label', default: 'UserTable'), userTableInstance.id])
                redirect userTableInstance
            }
            '*' { respond userTableInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UserTable userTableInstance) {

        if (userTableInstance == null) {
            notFound()
            return
        }

        userTableInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UserTable.label', default: 'UserTable'), userTableInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userTableInstance.label', default: 'UserTable'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    @Transactional
    def createUser(){
        initiateJSONParameters()
        validateCreateUserParams()
        if(jsonErrors?.size() == 0){
            UserTable userold

            if(params.loginType.toString().equals("facebook")) {
                userold = UserTable.findByUserNameAndLoginType(params.userName, params.loginType);
            }else{
                userold = UserTable.findByEmailAndLoginType(params.email, params.loginType);
            }

            if(userold){
                jsonErrors.push("user already registered")
            }else {
                UserTable user = new UserTable();
                user.email = params.email;
                user.userName = params.userName;
                user.gcm = params.gcm;
                user.loginType = params.loginType
                String token = UserHelperService.nextId();
                user.userToken = token;
                if (user.save(flush: true, failOnError: true)) {
                    Map userMap = new HashMap()
                    userMap.put("userToken", token)
                    jsonResponse.push(userMap)
                    jsonStatus = true
                } else {
                    jsonErrors.push("error occured while saving user");
                }
            }
        }

        renderResponse()
    }

    def authenticateUserTable(){
        initiateJSONParameters()
        validateCreateUserParams()
/*        if(UserTable.findByUserNameAndEmail(params.userName, params.email)){
            String token = UserTable.findByUserName(params.userName).userToken

            JSONObject responseObj = new JSONObject();
            responseObj.put("userToken", token);
            obj.put("success", responseObj)
            render  obj as JSON;
        }else{
            JSONObject responseObj = new JSONObject();
            responseObj.put("error","Check your user name and password");
            obj.put("error", responseObj)
            render  obj as JSON;
        }*/

        if(jsonErrors?.size() == 0){
            UserTable userold

            if(params.loginType.toString().equals("facebook")) {
                userold = UserTable.findByUserNameAndLoginType(params.userName, params.loginType);
            }else{
                userold = UserTable.findByEmailAndLoginType(params.email, params.loginType);
            }

            if(userold){
                jsonObject.put("userToken", userold.userToken)
                jsonResponse.push("successfully logged in")
                jsonStatus = true
                jsonResponse.push(jsonObject)
            }else {
                jsonErrors.push("invalid login. Please check username and password combination")
            }
        }
        renderResponse()

    }

    def validateCreateUserParams(){
        if(!params.loginType){
            jsonErrors.push("login type not found")
        }

        if(params.loginType && params.loginType.toString().equals("gmail")) {
            if (!params.email) {
                jsonErrors.push("email not found")
            }
        }

        if(!params.userName){
            jsonErrors.push("user name not found")
        }
    }

    @Transactional
    def createUser1(){
        initiateJSONParameters()
        validateCreateUserParams()
        if(jsonErrors?.size() == 0){
            UserTable userold

            if(params.loginType.toString().equals("facebook")) {
                userold = UserTable.findByUserNameAndLoginType(params.userName, params.loginType);
            }else{
                userold = UserTable.findByEmailAndLoginType(params.email, params.loginType);
            }

            if(userold){
                userold.gcm = params.gcm
                userold.save(failOnError: true)
                Map userMap = new HashMap()
                userMap.put("userToken", userold.userToken)
                jsonResponse.push(userMap)
                jsonStatus = true

            }else {
                UserTable user = new UserTable();
                user.email = params.email;
                user.userName = params.userName;
                user.gcm = params.gcm;
                user.name = params.name;
                user.loginType = params.loginType
                String token = UserHelperService.nextId();
                user.userToken = token;
                if (user.save(flush: true, failOnError: true)) {
                    Map userMap = new HashMap()
                    userMap.put("userToken", token)
                    jsonResponse.push(userMap)
                    jsonStatus = true
                } else {
                    jsonErrors.push("error occured while saving user");
                }
            }
        }

        renderResponse()
    }

    def getApiVersion(){

        initiateJSONParameters()

        try {

            def version = AppApiVersion.findAll()

            Map userMap = new HashMap()
            userMap.put( "versionNumber",  version.get(0).versionName )
            jsonResponse.push(userMap)
            jsonStatus = true

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
}
