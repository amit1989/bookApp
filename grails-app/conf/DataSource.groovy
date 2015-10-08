dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    username = "amit"
    password = "amit"
    dialect = org.hibernate.dialect.PostgreSQLDialect

}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class='org.hibernate.cache.EhCacheProvider'
}

environments {

    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")

            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }

    development {
        dataSource {
            url="jdbc:postgresql://localhost:5432/booklib"
            dbCreate = "create-drop"
            driverClassName = "org.postgresql.Driver"
            username = "amit"
            password = "amit"
        }
    }
}