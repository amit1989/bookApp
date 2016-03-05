package bookapp


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TutorCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TutorCategory.list(params), model: [tutorCategoryInstanceCount: TutorCategory.count()]
    }

    def show(TutorCategory tutorCategoryInstance) {
        respond tutorCategoryInstance
    }

    def create() {
        respond new TutorCategory(params)
    }

    @Transactional
    def save(TutorCategory tutorCategoryInstance) {
        if (tutorCategoryInstance == null) {
            notFound()
            return
        }

        if (tutorCategoryInstance.hasErrors()) {
            respond tutorCategoryInstance.errors, view: 'create'
            return
        }

        tutorCategoryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tutorCategoryInstance.label', default: 'TutorCategory'), tutorCategoryInstance.id])
                redirect tutorCategoryInstance
            }
            '*' { respond tutorCategoryInstance, [status: CREATED] }
        }
    }

    def edit(TutorCategory tutorCategoryInstance) {
        respond tutorCategoryInstance
    }

    @Transactional
    def update(TutorCategory tutorCategoryInstance) {
        if (tutorCategoryInstance == null) {
            notFound()
            return
        }

        if (tutorCategoryInstance.hasErrors()) {
            respond tutorCategoryInstance.errors, view: 'edit'
            return
        }

        tutorCategoryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TutorCategory.label', default: 'TutorCategory'), tutorCategoryInstance.id])
                redirect tutorCategoryInstance
            }
            '*' { respond tutorCategoryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TutorCategory tutorCategoryInstance) {

        if (tutorCategoryInstance == null) {
            notFound()
            return
        }

        tutorCategoryInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TutorCategory.label', default: 'TutorCategory'), tutorCategoryInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tutorCategoryInstance.label', default: 'TutorCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
