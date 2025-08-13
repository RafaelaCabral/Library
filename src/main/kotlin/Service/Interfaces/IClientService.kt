package org.example.Service.Interfaces

import org.example.Domain.Client

interface IClientService {
    fun register(client: Client): String?
    fun getAll(): Map<String, Client>
    fun getById(id: String): Client?
    fun update(id: String, client: Client): Boolean
    fun delete(id: String): Boolean
}