package org.example.Storage

import com.github.f4b6a3.ulid.UlidCreator
import org.example.Domain.Client
import org.example.Storage.Interfaces.IClientRepository


class ClientRepositoryMock: IClientRepository{
    override fun insert(client: Client): String {
        return UlidCreator.getUlid().toString()
    }

    override fun getAll(): Map<String, Client> {
        val client = createMockClient()
        val id= client.id.toString()
        return mapOf(id to client)
    }

    override fun get(id: String): Client? {
        return createMockClient()
    }

    fun createMockClient(): Client{
        return Client(
            id = UlidCreator.getUlid().toString(),
            name = "Leticia Maria",
            email = "maria@leticia.alan",
            phone = "12988990066"
        )

    }
}