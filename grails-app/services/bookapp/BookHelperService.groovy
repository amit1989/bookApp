package bookapp

import grails.transaction.Transactional

@Transactional
class BookHelperService {

    def serviceMethod() {

    }


    public HashMap getBookHasMap(def book){

        HashMap jsonMap = new HashMap()
        jsonMap.students = book.collect { books ->
            return ["id": books.id,
                    "title": books.title,
                    "isbn": books.isbn,
                    "discreption": books.discreption,
                    "author": books.author,
                    "image_url": books.url,
                    "category": books.category.name,
                    "isShared": books.isShared,
                    "isOnSell": books.isOnSell,
                    "startDate": books.startDate,
                    "isDonated": books.isDonated,
                    "endDate": books.endDate,
                    "originalCost": books.originalCost,
                    "discount": books.discount,
                    "bookEdition": books.bookEdition,
                    "yearOfBook" :books.yearOfBook,
                    "isCompleted": books.isCompleted,
                    "onCondition": books.onCondition,
                    "dateCreated": books.dateCreated,
                    "userName": books.user?.userName,
                    "userEmail": books.user?.email]
        }

        return jsonMap;

    }
}
