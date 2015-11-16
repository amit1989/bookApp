package bookapp

import com.google.android.gcm.server.MulticastResult
import com.google.android.gcm.server.Result
import grails.transaction.Transactional
import org.json.simple.JSONObject

@Transactional
class BookHelperService {


    def androidGcmService;

    def serviceMethod() {

    }


    //if



    def boolean prepareAndroidNotification(String regId, String requestType, String details){
        try{
            if(!regId){
                log.info("Registration Id null, GCM Notification sent failed")
                return false
            }
            Map messages = preparePayLoadData(requestType, details);

            //log.info("prepareAndroidNotification start")
            //log.info(status)
            //log.info(msgText)
            //log.info(messages)
            //log.info("prepareAndroidNotification end")
            return sendAndroidNotification(messages,[regId],"")
        }
        catch (Exception e){
            return false
            //throw (e)
        }
    }
    private Map preparePayLoadData(String requestType, String details){

        Map messages = [:]


        if(!details.equals("")){

            if(requestType.equals("requestToken")){

                messages['Message'] = "Request Token Generated : "+details
            }

            if(requestType.equals("notifySeller")){

                messages['Message'] = details+" : has shown intrest in your book. "
            }


        }






        return messages
    }

    private boolean sendAndroidNotification(Map data, List<String> registrationIds, String collapseKey) {
        try
        {
            println("Android Push Notification data ----->")
            println("Payload Data ----->"+data)
            println("Registration Ids ----->"+registrationIds)
            //def gcmApiKey = grailsApplication.config.GCM_API_KEY
            def gcmApiKey = "AIzaSyDjUJuSrQ9XgkIQFK6Ttmqm8EzR0DlqFQ0"
            println("GCM API Key ----->"+gcmApiKey)
            def outPut
            if(gcmApiKey && registrationIds && data) {
                outPut = androidGcmService.sendMessage(data, registrationIds, "", gcmApiKey)
                println("result --->" +outPut.getClass())
                if(outPut instanceof MulticastResult){
                    log.info(outPut)
                    def resultList = outPut.getResults()
                    if(resultList){
                        //resultList.each {Result result ->
                        for (Result result : resultList){
                            log.info(result)
                            log.info(result.getErrorCodeName())
                        }
                    }
                }
                else if(outPut instanceof Result){
                    if(outPut.getErrorCodeName()){
                        return false
                    }
                }
                return true
            }
            else{
                return false
            }
        }
        catch(Exception e)
        {
            e.printStackTrace()
            println("Exception in Android Push Notification Service")
            return false
        }
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
                    "shareCount": books.shareCount,
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