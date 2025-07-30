package org.example.Connection

import java.sql.Connection
import java.sql.DriverManager
import io.github.cdimascio.dotenv.dotenv


object ConexaoDB {
    private val dotenv = dotenv {
        directory = System.getProperty("user.dir") // raiz do projeto
        ignoreIfMissing = false
    }

    private val host = dotenv["DB_HOST"]
    private val port = dotenv["DB_PORT"]
    private val dbName = dotenv["DB_NAME"]
    private val user = dotenv["DB_USER"]
    private val password = dotenv["DB_PASSWORD"]
    private val url = "jdbc:postgresql://localhost:5432/library"
    fun getConnection(): Connection {
        return DriverManager.getConnection(url, user, password)
}
}