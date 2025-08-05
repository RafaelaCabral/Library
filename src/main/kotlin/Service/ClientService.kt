package org.example.Service

import org.example.Domain.Client
import org.example.Service.Interfaces.IClientService
import org.example.Storage.ClientRepository


class ClientService : IClientService {
    private val repository = ClientRepository()
    override fun registerClient(client: Client) {
        if (client.client_name.isBlank() || client.client_email.isBlank() || client.client_phone.isBlank()) {
            println("Campos obrigatórios em branco")
            return
        }
        repository.insertClient(client)
    }

}