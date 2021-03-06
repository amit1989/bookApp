package bookapp

import org.grails.databinding.BindingFormat

import java.text.SimpleDateFormat

class Book {

/*    *//* Default (injected) attributes of GORM *//*
    Long    id
    Long    version

    *//* Automatic timestamping of GORM *//*
    Date    dateCreated
    Date    lastUpdated*/


    String isbn
    String title
    String discreption
    String author
    String url
    Category category
    Boolean isShared
    Boolean isOnSell
    Boolean isDonated
    String startDate
    String endDate
    String originalCost
    Integer discount
    String bookEdition
    String yearOfBook
    Boolean isCompleted = false
    String onCondition
    Integer shareCount
    UserTable user
//    String image
    String imageUrl
    @BindingFormat('yyyy-MM-dd HH:mm:ss.S')
    Date    dateCreated


//    static belongsTo = [user: User]

    static constraints = {

        title(blank: false)
        isbn(nullable: true)
        discreption(nullable: true)
        category(nullable: true)
        startDate(nullable: true)
        endDate(nullable: true)
        originalCost(nullable: true)
        bookEdition(nullable: true)
        yearOfBook(nullable: true)
        onCondition(nullable: true)
        user(nullable: true)
        isShared(nullable: true)
        isDonated(nullable: true)
        isCompleted(nullable: true)
        discount(nullable: true)
        isOnSell(nullable: true)
        author(nullable: true)
        url(nullable: true)
        shareCount(nullable: true)
//        image(nullable: true)
        imageUrl(nullable: true)
    }


    String bookDetail(){
        return category
    }

    String bookCategoryByID(){
        return category.id
    }

    static def  getUserIDByToken(String token){
        return  UserTable.findByUserToken(token)
    }
    static String getTimeStamp(){
        SimpleDateFormat sdfdate = new SimpleDateFormat("yyyyMMddHHmmss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfdate.format(now);
        return strDate;
    }
}