package bookapp


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PickupLocationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PickupLocation.list(params), model: [pickupLocationInstanceCount: PickupLocation.count()]
    }

    def show(PickupLocation pickupLocationInstance) {
        respond pickupLocationInstance
    }

    def create() {
        respond new PickupLocation(params)
    }

    @Transactional
    def save(PickupLocation pickupLocationInstance) {
        if (pickupLocationInstance == null) {
            notFound()
            return
        }

        if (pickupLocationInstance.hasErrors()) {
            respond pickupLocationInstance.errors, view: 'create'
            return
        }

        pickupLocationInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pickupLocationInstance.label', default: 'PickupLocation'), pickupLocationInstance.id])
                redirect pickupLocationInstance
            }
            '*' { respond pickupLocationInstance, [status: CREATED] }
        }
    }

    def edit(PickupLocation pickupLocationInstance) {
        respond pickupLocationInstance
    }

    @Transactional
    def update(PickupLocation pickupLocationInstance) {
        if (pickupLocationInstance == null) {
            notFound()
            return
        }

        if (pickupLocationInstance.hasErrors()) {
            respond pickupLocationInstance.errors, view: 'edit'
            return
        }

        pickupLocationInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PickupLocation.label', default: 'PickupLocation'), pickupLocationInstance.id])
                redirect pickupLocationInstance
            }
            '*' { respond pickupLocationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PickupLocation pickupLocationInstance) {

        if (pickupLocationInstance == null) {
            notFound()
            return
        }

        pickupLocationInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PickupLocation.label', default: 'PickupLocation'), pickupLocationInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pickupLocationInstance.label', default: 'PickupLocation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
