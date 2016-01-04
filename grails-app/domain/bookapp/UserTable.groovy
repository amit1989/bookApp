package bookapp

class UserTable {

    String userName;
    String email;
    String userToken;
    String gcm;
    String status;
    String loginType;

    static constraints = {
        userName(blank: false)
        email(blank: false)
        userToken(blank: false)
        gcm(nullable: true)
        status(nullable: true)
        loginType(nullable: true)
    }


}
