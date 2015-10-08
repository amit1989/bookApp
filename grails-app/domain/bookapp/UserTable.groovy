package bookapp

class UserTable {

    String userName;
    String email;
    String userToken;
    String gcm;
    String status;

    static constraints = {
        userName(blank: false, unique: true)
        email(blank: false, unique: true)
        userToken(blank: false)
        gcm(nullable: true)
        status(nullable: true)
    }


}
