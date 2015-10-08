package bookapp

class Category {

    String name
    String discription

    static constraints = {

        name(blank: false)
        discription(nullable: true)
    }

    String toString(){
        return name
    }
}
