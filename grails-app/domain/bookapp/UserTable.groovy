package bookapp

class UserTable {
	String name
    String userName;
    String email;
    String userToken;
    String gcm;
    String status;
    String loginType;
    String mobileNumber;
    boolean verified;

    static constraints = {
		name (nullable: true)
        userName(blank: false)
        email(nullable: true)
        userToken(blank: false)
        gcm(nullable: true)
        status(nullable: true)
        loginType(nullable: true)
        mobileNumber(nullable: true)
        verified(nullable: true)
    }

    static mapping = {
        name verified: true
    }
}
