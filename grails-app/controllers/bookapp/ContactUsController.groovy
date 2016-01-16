package bookapp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContactUsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContactUs.list(params), model:[contactUsInstanceCount: ContactUs.count()]
    }

    def show(ContactUs contactUsInstance) {
        respond contactUsInstance
    }

    def create() {
        respond new ContactUs(params)
    }

    @Transactional
    def save(ContactUs contactUsInstance) {
        if (contactUsInstance == null) {
            notFound()
            return
        }

        if (contactUsInstance.hasErrors()) {
            respond contactUsInstance.errors, view:'create'
            return
        }

        contactUsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contactUsInstance.label', default: 'ContactUs'), contactUsInstance.id])
                redirect contactUsInstance
            }
            '*' { respond contactUsInstance, [status: CREATED] }
        }
    }

    def edit(ContactUs contactUsInstance) {
        respond contactUsInstance
    }

    @Transactional
    def update(ContactUs contactUsInstance) {
        if (contactUsInstance == null) {
            notFound()
            return
        }

        if (contactUsInstance.hasErrors()) {
            respond contactUsInstance.errors, view:'edit'
            return
        }

        contactUsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ContactUs.label', default: 'ContactUs'), contactUsInstance.id])
                redirect contactUsInstance
            }
            '*'{ respond contactUsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ContactUs contactUsInstance) {

        if (contactUsInstance == null) {
            notFound()
            return
        }

        contactUsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ContactUs.label', default: 'ContactUs'), contactUsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contactUsInstance.label', default: 'ContactUs'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
