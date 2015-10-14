package bookapp

class PickupLocation {

    String addressOne
    String addressTwo
    String city
    String latitude
    String longitude
    String mobileNumber
    Book book
    UserTable user

    static constraints = {
        addressOne(nullable: true)
        addressTwo(nullable: true)
        city(nullable: true)
        latitude(nullable: true)
        longitude(nullable: true)
        mobileNumber(nullable: true)
        book(nullable: true)
        user(nullable: true)
    }
}
