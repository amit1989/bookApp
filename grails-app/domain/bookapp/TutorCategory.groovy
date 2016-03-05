package bookapp

class TutorCategory {


    String name
    String details

    static constraints = {
        name(blank: false)
        details(nullable: true)
    }

    String toString() {
        return name
    }
}
