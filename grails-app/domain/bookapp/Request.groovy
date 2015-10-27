package bookapp

class Request {

    UserTable user;
    Book book;
    Boolean is_completed;
    String requestToken;
    String sharedDate


    static constraints = {
        user(nullable: true)
        book(nullable: true)
        is_completed(nullable: true)
        requestToken(blank: true)
        sharedDate(nullable: true )
    }
}
