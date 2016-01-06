package bookapp

class UserTable {
	String name
    String userName;
    String email;
    String userToken;
    String gcm;
    String status;
    String loginType;

    static constraints = {
		name (nullable: true)
        userName(blank: false)
        email(nullable: true)
        userToken(blank: false)
        gcm(nullable: true)
        status(nullable: true)
        loginType(nullable: true)
    }
}
