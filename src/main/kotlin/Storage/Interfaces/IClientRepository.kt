package org.example.Storage.Interfaces

import org.example.Domain.Client

interface IClientRepository {
    fun insert(client: Client): String
    fun getAll(): Map<String, Client>
    fun get(id: String): Client?
    fun update(id: String, client: Client): Boolean
    fun delete(id:String): Boolean
}