package bookapp


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BookImageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BookImage.list(params), model: [bookImageInstanceCount: BookImage.count()]
    }

    def show(BookImage bookImageInstance) {
        respond bookImageInstance
    }

    def create() {
        respond new BookImage(params)
    }

    @Transactional
    def save(BookImage bookImageInstance) {
        if (bookImageInstance == null) {
            notFound()
            return
        }

        if (bookImageInstance.hasErrors()) {
            respond bookImageInstance.errors, view: 'create'
            return
        }

        if(bookImageInstance.save(flush: true, failOnError: true)){
            if(request.getFile('photo')) {
                def uploadedFile = request.getFile('photo')
                def webRootDir = servletContext.getRealPath("/")
                println "webRootDir: " + webRootDir
                def userDir = new File(webRootDir, "/images/books")
                println "userDir: " + userDir
                userDir.mkdirs()
                uploadedFile.transferTo(new File(userDir, bookImageInstance.isbnNumber + ".jpg"))
            }
            bookImageInstance.isImageFound = true
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bookImageInstance.label', default: 'BookImage'), bookImageInstance.id])
                redirect bookImageInstance
            }
            '*' { respond bookImageInstance, [status: CREATED] }
        }
    }

    def edit(BookImage bookImageInstance) {
        respond bookImageInstance
    }

    @Transactional
    def update(BookImage bookImageInstance) {
        if (bookImageInstance == null) {
            notFound()
            return
        }

        if (bookImageInstance.hasErrors()) {
            respond bookImageInstance.errors, view: 'edit'
            return
        }

        bookImageInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BookImage.label', default: 'BookImage'), bookImageInstance.id])
                redirect bookImageInstance
            }
            '*' { respond bookImageInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BookImage bookImageInstance) {

        if (bookImageInstance == null) {
            notFound()
            return
        }

        bookImageInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BookImage.label', default: 'BookImage'), bookImageInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookImageInstance.label', default: 'BookImage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
