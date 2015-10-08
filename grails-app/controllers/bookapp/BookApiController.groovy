package bookapp

import grails.rest.RestfulController

class BookApiController extends RestfulController {
    static responseFormats = ['json', 'xml']
    BookApiController(){
        super(Book)
    }

}
