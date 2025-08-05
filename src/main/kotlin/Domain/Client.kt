package org.example.Domain

import kotlinx.serialization.Serializable

@Serializable
class ClientRequest(
    val name: String,
    val email: String,
    val phone: String
)
@Serializable
class Client (
    var id: String? = null,
    var name: String = String(),
    var email: String = String(),
    var phone: String = String()
) {
    constructor(client: ClientRequest) : this() {
        this.name = client.name
        this.email = client.email
        this.phone = client.phone
    }

}