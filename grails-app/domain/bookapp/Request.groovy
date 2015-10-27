package bookapp

class Request {

    UserTable user;
    Book book;
    Boolean is_completed;
    String requestToken;


    static constraints = {
        user(nullable: true)
        book(nullable: true)
        is_completed(nullable: true)
        requestToken(blank: true)
    }
}
