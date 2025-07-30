package org.example

import org.example.Connection.ConexaoDB
import java.sql.SQLException

fun main() {
    println("Testando conexão")
    try {
        val connection = ConexaoDB.getConnection()
        println("Conexão estabelecida")

        val stmt = connection.createStatement()
        val resultSet = stmt.executeQuery("SELECT version();")

        if (resultSet.next()) {
            println("Versão do PostgreSQL: ${resultSet.getString(1)}")
        }
    } catch (e: SQLException){
        println("Não foi possível fazer conexão")
    }
}