package bookapp


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TagsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tags.list(params), model: [tagsInstanceCount: Tags.count()]
    }

    def show(Tags tagsInstance) {
        respond tagsInstance
    }

    def create() {
        respond new Tags(params)
    }

    @Transactional
    def save(Tags tagsInstance) {
        if (tagsInstance == null) {
            notFound()
            return
        }

        if (tagsInstance.hasErrors()) {
            respond tagsInstance.errors, view: 'create'
            return
        }

        tagsInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tagsInstance.label', default: 'Tags'), tagsInstance.id])
                redirect tagsInstance
            }
            '*' { respond tagsInstance, [status: CREATED] }
        }
    }

    def edit(Tags tagsInstance) {
        respond tagsInstance
    }

    @Transactional
    def update(Tags tagsInstance) {
        if (tagsInstance == null) {
            notFound()
            return
        }

        if (tagsInstance.hasErrors()) {
            respond tagsInstance.errors, view: 'edit'
            return
        }

        tagsInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tags.label', default: 'Tags'), tagsInstance.id])
                redirect tagsInstance
            }
            '*' { respond tagsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Tags tagsInstance) {

        if (tagsInstance == null) {
            notFound()
            return
        }

        tagsInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tags.label', default: 'Tags'), tagsInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tagsInstance.label', default: 'Tags'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
