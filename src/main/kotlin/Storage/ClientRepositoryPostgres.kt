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
        TODO("Not yet implemented")
    }

    override fun get(id: String): Client? {
        TODO("Not yet implemented")
    }


}