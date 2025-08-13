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

    override fun update(id: String, client: Client): Boolean {
        return if (data.containsKey(id)) {
            client.id = id
            data[id] = client
            println("Memory: Client with ID $id updated")
            true
        } else {
            println("Memory: Client with ID $id not found for update")
            false
        }
    }

    override fun delete(id: String): Boolean {
        return if (data.remove(id) != null) {
            println("Memory: Client with ID $id deleted")
            true
        } else {
            println("Memory: Client with ID $id not found for deletion")
            false
        }
    }
}