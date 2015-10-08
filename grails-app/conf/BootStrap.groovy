import bookapp.Book
import bookapp.Category
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        def result = '################## running in UNCLEAR mode.'
        println "Application starting ... "
        switch (Environment.current) {
            case Environment.DEVELOPMENT:

                result = 'now running in DEV mode.'
                seedTestData()
                break;
            case Environment.TEST:
                result = 'now running in TEST mode.'
                break;
            case Environment.PRODUCTION:
                result = 'now running in PROD mode.'
//                seedProdData()
                break;
        }
        println "current environment: $Environment.current"
        println "$result"
    }

    def destroy = {
        println "Application shutting down... "
    }

    private void seedTestData() {
/*        println "Start loading cities into database"
        Book book = new Book(title: 'Complete Refrence Java', discreption: 'Best Book For Java', isbn: '235-456-78987')
        book.save(failOnError:true, flush:true, insert: true)
        book.errors = null*/
    }
}
