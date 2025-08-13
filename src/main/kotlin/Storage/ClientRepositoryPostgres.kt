package org.example.Storage

import com.github.f4b6a3.ulid.UlidCreator
import org.example.Connection.ConexaoDB
import org.example.Domain.Client
import org.example.Storage.Interfaces.IClientRepository

class ClientRepositoryPostgres: IClientRepository {

    override fun insert(client: Client):String {
        val ulid = UlidCreator.getUlid().toString()

        val sql = """
        INSERT INTO client
        (client_id, client_name, client_email, client_phone)
        VALUES
        (?,?,?,?)
    """.trimIndent()

        ConexaoDB.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, ulid)
                stmt.setString(2, client.name)
                stmt.setString(3, client.email)
                stmt.setString(4, client.phone)
                stmt.executeUpdate()
            }
        }
        println("Client inserted with ULID: $ulid")
        return ulid
    }

    override fun getAll(): Map<String, Client> {
        val clients = mutableMapOf<String, Client>()
        val sql = "SELECT * FROM client"
        ConexaoDB.getConnection().use { conn ->
            conn.prepareStatement(sql).use {stmt ->
                val rs = stmt.executeQuery()
                while (rs.next()){
                    val id = rs.getString("client_id")
                    val name = rs.getString("client_name")
                    val email = rs.getString("client_email")
                    val phone = rs.getString("client_phone")

                    val client = Client(name, email, phone)
                    clients[id] = client
                }

            }
        }
        return clients
    }

    override fun get(id: String): Client? {
        val sql = "SELECT * FROM client WHERE client_id = ?"
        ConexaoDB.getConnection().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id)

                val rs = stmt.executeQuery()
                if (rs.next()) {
                    return Client(
                        id = rs.getString("client_id"),
                        name = rs.getString("client_name"),
                        email = rs.getString("client_email"),
                        phone = rs.getString("client_phone")
                    )
                }
            }
        }
        return null
    }
    override fun update(id: String, client: Client): Boolean {
        val sql = """
        UPDATE client 
        SET client_name = ?, client_email = ?, client_phone = ?
        WHERE client_id = ?
    """.trimIndent()

        return try {
            ConexaoDB.getConnection().use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, client.name)
                    stmt.setString(2, client.email)
                    stmt.setString(3, client.phone)
                    stmt.setString(4, id)
                    val rows = stmt.executeUpdate()
                    println("Update executed, rows affected: $rows")
                    rows > 0
                }
            }
        } catch (e: Exception) {
            println("Error updating client: ${e.message}")
            false
        }
    }

    override fun delete(id: String): Boolean {
        val sql = "DELETE FROM client WHERE client_id = ?"

        return try {
            ConexaoDB.getConnection().use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id)
                    val rows = stmt.executeUpdate()
                    println("Delete executed, rows affected: $rows")
                    rows > 0
                }
            }
        } catch (e: Exception) {
            println("Error deleting client: ${e.message}")
            false
        }
    }


}