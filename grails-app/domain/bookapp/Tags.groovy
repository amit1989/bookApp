package bookapp

class Tags {

    Book book;
    PickupLocation location;
    String tags
    String detail

    static constraints = {
        book(nullable: true)
        location(nullable: true)
        tags(nullable: true)
        detail(nullable: true)
    }
}
