package org.example.Service

import org.example.Domain.Client
import org.example.Service.Interfaces.IClientService
import org.example.Storage.ClientRepositoryFactory
import org.example.Storage.ClientRepositoryType


class ClientService : IClientService {
    private val repository = ClientRepositoryFactory().create(ClientRepositoryType.Postgres)
    override fun register(client: Client): String? {
        if (client.name.isBlank() || client.email.isBlank() || client.phone.isBlank()) {
            println("Campos obrigatórios em branco")
            return null
        }
        return repository.insert(client)
    }

    override fun getAll(): Map<String, Client> {
        return repository.getAll()
    }

    override fun getById(id: String): Client? {
        return try {
            repository.get(id)
        } catch (e: Exception) {
            println("Error getting client by ID: ${e.message}")
            null
        }
    }

    override fun update(id: String, client: Client): Boolean {
        return try {
            repository.update(id, client)
        } catch (e: Exception) {
            println("Error updating client: ${e.message}")
            false
        }
    }

    override fun delete(id: String): Boolean {
        return try {
            repository.delete(id)
        } catch (e: Exception) {
            println("Error deleting client: ${e.message}")
            false
        }
    }



}
