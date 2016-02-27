package bookapp

import com.google.android.gcm.server.MulticastResult
import com.google.android.gcm.server.Result
import grails.transaction.Transactional
import org.json.simple.JSONObject

import java.text.SimpleDateFormat

@Transactional
class BookHelperService {


    def androidGcmService;

    def serviceMethod() {

    }


    public String generateRequestToken()
    {
        //generate a 4 digit integer 1000 <10000
        int randomPIN = (int)(Math.random()*9000)+1000;
        //Store integer in a string
        return  String.valueOf(randomPIN)
    }



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

                messages['Message'] = details+" : has shown interest in your book. "
            }

            if (requestType.equals("notifyAllUser")){
                messages['status'] = "notifyAllUser"
                messages['url'] = "notifyAllUser"
                messages['message'] = "notifyAllUser"
            }

            if(requestType.equals("confirmBookRequest")){
                messages['Message'] = details
                messages['status'] = "confirmBookRequest"

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
            def gcmApiKey = "AIzaSyA9BWi2vUILOeoWPTbnvz6W_vfaFCiSKeA"
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

    public HashMap getBookHasMap(def bookList){
        HashMap jsonMap = new HashMap()
        jsonMap.books = bookList.collect { books ->
            return [
                "id"            : books?.id ?: "",
                "title"         : books?.title ?: "",
                "isbn"          : books?.isbn ?: "",
                "discreption"   : books?.discreption ?: "",
                "author"        : books?.author ?: "",
                "image_url"     : books?.imageUrl ?: "",
                "category"      : books?.category?.name ?: "",
                "isShared"      : books?.isShared ?: "",
                "isOnSell"      : books?.isOnSell ?: "",
                "startDate"     : books?.startDate ?: "",
                "isDonated"     : books?.isDonated ?: "",
                "endDate"       : books?.endDate ?: "",
                "originalCost"  : books?.originalCost ?: "",
                "discount"      : books?.discount ?: "",
                "bookEdition"   : books?.bookEdition ?: "",
                "yearOfBook"    : books?.yearOfBook ?: "",
                "isCompleted"   : books?.isCompleted ?: "",
                "onCondition"   : books?.onCondition ?: "",
                "dateCreated"   : books?.dateCreated ? new SimpleDateFormat("dd/MM/yyyy hh:MM:ss").format(books.dateCreated) : "",
                "shareCount"    : books?.shareCount ?: "",
                "imageName"     : books?.imageUrl ?: "",
                "userName"      : books?.user?.userName ?: "",
                "userEmail"     : books?.user?.email ?: "",
                "name"          : books?.user?.name ?: "",
                "verified"      : books?.user?.verified ?: ""
            ]
        }
        return jsonMap;
    }

    public JSONObject getBooksList(def bookList){
        HashMap booksMap = new HashMap()
        booksMap.books = bookList.collect { books ->
            return [
                "id"            : books?.id ?: "",
                "title"         : books?.title ?: "",
                "isbn"          : books?.isbn ?: "",
                "discreption"   : books?.discreption ?: "",
                "author"        : books?.author ?: "",
                "image_url"     : books?.imageUrl ?: "",
                "category"      : books?.category?.name ?: "",
                "isShared"      : books?.isShared ?: "",
                "isOnSell"      : books?.isOnSell ?: "",
                "startDate"     : books?.startDate ?: "",
                "isDonated"     : books?.isDonated ?: "",
                "endDate"       : books?.endDate ?: "",
                "originalCost"  : books?.originalCost ?: "",
                "discount"      : books?.discount ?: "",
                "bookEdition"   : books?.bookEdition ?: "",
                "yearOfBook"    : books?.yearOfBook ?: "",
                "isCompleted"   : books?.isCompleted ?: "",
                "onCondition"   : books?.onCondition ?: "",
                "dateCreated"   : books?.dateCreated ? new SimpleDateFormat("dd/MM/yyyy hh:MM:ss").format(books.dateCreated) : "",
                "shareCount"    : books?.shareCount ?: "",
                "imageName"     : books?.imageUrl ?: "",
                "userName"      : books?.user?.userName ?: "",
                "userEmail"     : books?.user?.email ?: "",
                "name"          : books?.user?.name ?: "",
                "verified"      : books?.user?.verified ?: ""
            ]
        }

        return booksMap
    }
	
    public JSONObject getBookAsJson(Book books){

        JSONObject object = new JSONObject();

        object.put("id", books.id)
        object.put("title", books.title)
        object.put("isbn", books?.isbn ?: "")
        object.put("discreption", books?.discreption ?: "")
        object.put("author", books?.author ?: "")
        object.put("image_url", books?.url ?: "")
        object.put("category", books?.category?.name ?: "")
        object.put("isShared", books?.isShared ?: "")
        object.put("isOnSell", books?.isOnSell ?: "")
        object.put("startDate", books?.startDate ?: "")
        object.put("isDonated", books?.isDonated ?: "")
        object.put("endDate", books?.endDate ?: "")
        object.put( "originalCost", books?.originalCost ?: "")
        object.put("discount", books?.discount ?: "")
        object.put("bookEdition", books?.bookEdition ?: "")
        object.put("yearOfBook" , books?.yearOfBook ?: "")
        object.put("isCompleted", books?.isCompleted ?: "")
        object.put("onCondition", books?.onCondition ?: "")
        object.put("dateCreated", books?.dateCreated ? new SimpleDateFormat("dd/MM/yyyy hh:MM:ss").format(books.dateCreated) : "")
        object.put("imageName", books?.imageUrl ?: "")
        object.put("userName", books?.user?.userName ?: "")
        object.put("userEmail", books?.user?.email ?: "")
        object.put("name", books?.user?.name ?: "")
        object.put("verified", books?.user?.verified ?: "")
        return object;
    }
}
