package org.example.Storage

import org.example.Storage.Interfaces.IClientRepository

enum class ClientRepositoryType{
    Postgres,
    Mock,
    Memory
}
class ClientRepositoryFactory{
    fun create(type: ClientRepositoryType): IClientRepository{
        val ret = when(type){
            ClientRepositoryType.Postgres -> ClientRepositoryPostgres()
            ClientRepositoryType.Memory -> ClientRepositoryMemory()
            else -> ClientRepositoryMock()
        }
        return ret
    }
}