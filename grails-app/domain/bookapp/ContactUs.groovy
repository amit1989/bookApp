package bookapp

class ContactUs {

    String name;
    String contactNumber;
    String details;

    static constraints = {
        name(nullable: true);
        contactNumber(nullable: true);
        details(nullable: true);

    }
}
