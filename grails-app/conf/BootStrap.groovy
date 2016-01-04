class BootStrap {

    def init = { servletContext ->
        println("Changing timezone to GMT+0530")
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0530"))
    }

    def destroy = {

    }
}
