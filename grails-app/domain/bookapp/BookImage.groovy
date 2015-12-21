package bookapp

/**
 * Created by Administrator1 on 12/20/2015.
 */
class BookImage {
    String isbnNumber
    boolean isImageFound = false
    /*byte photo*/

    static constraints = {
        isbnNumber (nullable: true, unique: true)
        isImageFound (nullable: true)
        /*photo (nullable: true)*/
    }
}