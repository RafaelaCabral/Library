package org.example.Service

import org.example.Domain.Client
import org.example.Service.Interfaces.IClientService
import org.example.Storage.ClientRepositoryFactory
import org.example.Storage.ClientRepositoryType


class ClientService : IClientService {
    private val repository = ClientRepositoryFactory().create(ClientRepositoryType.Memory)
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

}

//melhorias: criar um factory