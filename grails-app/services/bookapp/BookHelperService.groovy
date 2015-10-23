package bookapp

import grails.transaction.Transactional
import org.json.simple.JSONObject

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

    public JSONObject getBookAsJson(Book books){

        JSONObject object = new JSONObject();

        object.put("id", books.id)
        object.put("title", books.title)
        object.put("isbn", books.isbn)
        object.put("discreption", books.discreption)
        object.put("author", books.author)
        object.put("image_url", books.url)
        object.put("category", books.category.name)
        object.put("isShared", books.isShared)
        object.put("isOnSell", books.isOnSell)
        object.put("startDate", books.startDate)
        object.put("isDonated", books.isDonated)
        object.put("endDate", books.endDate)
        object.put( "originalCost", books.originalCost)
        object.put("discount", books.discount)
        object.put("bookEdition", books.bookEdition)
        object.put("yearOfBook" , books.yearOfBook)
        object.put("isCompleted", books.isCompleted)
        object.put("onCondition", books.onCondition)
        object.put("dateCreated", books.dateCreated)
        object.put("userName", books.user?.userName)
        object.put("userEmail", books.user?.email)

        return object;
    }
}
