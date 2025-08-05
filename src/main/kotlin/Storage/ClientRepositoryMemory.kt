package org.example.Storage

import com.github.f4b6a3.ulid.UlidCreator
import org.example.Domain.Client
import org.example.Storage.Interfaces.IClientRepository

class ClientRepositoryMemory: IClientRepository {
    private val data = mutableMapOf<String, Client>()
    override fun insert(client: Client): String {
        val id = UlidCreator.getUlid().toString()
        data.put(id, client)
        return id
    }

    override fun getAll(): Map<String, Client> {
        return data
    }

    override fun get(id: String): Client? {
        return data[id]
    }
}