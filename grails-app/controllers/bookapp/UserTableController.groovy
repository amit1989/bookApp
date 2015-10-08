package bookapp

import grails.converters.JSON
import org.json.simple.JSONObject

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserTableController {

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


    def UserHelperService userHelperService

    @Transactional
    def createUser(){
        JSONObject obj = new JSONObject();

        try{

            UserTable user = new UserTable();
            user.email = params.email;
            user.userName = params.userName;
            user.gcm = params.gcm;
            String token  = userHelperService.nextId();
            user.userToken = token;

            if(user.save( flush: true , failOnError: true)){
                println('saving...........')

                JSONObject responseObj = new JSONObject();
                responseObj.put("userToken", token);
                obj.put("success", responseObj)
                render  obj as JSON;
            }else{
                JSONObject responseObj = new JSONObject();
                responseObj.put("error", "failed");
                obj.put("error", responseObj)
                render  obj as JSON;
            }


        }catch (Exception e){
            JSONObject responseObj = new JSONObject();
            responseObj.put("error", e.getMessage());
            obj.put("error", responseObj)
            render  obj as JSON;
        }
    }

    def authenticateUserTable(){
        JSONObject obj = new JSONObject();
        if(UserTable.findByUserNameAndEmail(params.userName, params.email)){
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
        }
    }
}
