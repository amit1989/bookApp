package bookapp


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AppApiVersionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AppApiVersion.list(params), model: [appApiVersionInstanceCount: AppApiVersion.count()]
    }

    def show(AppApiVersion appApiVersionInstance) {
        respond appApiVersionInstance
    }

    def create() {
        respond new AppApiVersion(params)
    }

    @Transactional
    def save(AppApiVersion appApiVersionInstance) {
        if (appApiVersionInstance == null) {
            notFound()
            return
        }

        if (appApiVersionInstance.hasErrors()) {
            respond appApiVersionInstance.errors, view: 'create'
            return
        }

        appApiVersionInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appApiVersionInstance.label', default: 'AppApiVersion'), appApiVersionInstance.id])
                redirect appApiVersionInstance
            }
            '*' { respond appApiVersionInstance, [status: CREATED] }
        }
    }

    def edit(AppApiVersion appApiVersionInstance) {
        respond appApiVersionInstance
    }

    @Transactional
    def update(AppApiVersion appApiVersionInstance) {
        if (appApiVersionInstance == null) {
            notFound()
            return
        }

        if (appApiVersionInstance.hasErrors()) {
            respond appApiVersionInstance.errors, view: 'edit'
            return
        }

        appApiVersionInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AppApiVersion.label', default: 'AppApiVersion'), appApiVersionInstance.id])
                redirect appApiVersionInstance
            }
            '*' { respond appApiVersionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AppApiVersion appApiVersionInstance) {

        if (appApiVersionInstance == null) {
            notFound()
            return
        }

        appApiVersionInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AppApiVersion.label', default: 'AppApiVersion'), appApiVersionInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appApiVersionInstance.label', default: 'AppApiVersion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
