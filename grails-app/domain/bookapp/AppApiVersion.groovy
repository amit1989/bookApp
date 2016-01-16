package bookapp

class AppApiVersion {

    String versionName
    String uploadedDate

    static constraints = {
        versionName(nullable: true)
        uploadedDate(nullable: true)
    }
}
