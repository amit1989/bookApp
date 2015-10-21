package bookapp

class WishList {

    UserTable user
    String bookRef


    static constraints = {
        user(nullable: true)
        bookRef(nullable: true)
    }
}
