package org.example.Storage.Interfaces

import org.example.Domain.Client

interface IClientRepository {
    fun insertClient(client: Client)
}