package bookapp

import org.grails.databinding.BindingFormat

class Tutor {

    UserTable user
    String instituteName
    String cources
    String details
    String averageRating
    String imageUrl
    @BindingFormat('yyyy-MM-dd HH:mm:ss.S')
    Date    dateCreated
    TutorCategory category

    static constraints = {
        instituteName(nullable: true)
        cources(nullable: true)
        details(nullable: true)
        averageRating(nullable: true)
        imageUrl(nullable: true)
        category(nullable: true)
    }
}
