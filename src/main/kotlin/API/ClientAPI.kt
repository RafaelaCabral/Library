package org.example.API

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.example.Domain.Client
import org.example.Service.ClientService
import java.util.*

@Serializable
data class ClientRequest(
    val client_name: String,
    val client_email: String,
    val client_phone: String
)

fun Route.clientAPI() {
    val service = ClientService()

    post("/insertClients") {
        val request = call.receive<ClientRequest>()

        val client = Client(
            client_name = request.client_name,
            client_email = request.client_email,
            client_phone = request.client_phone
        )


        service.registerClient(client)

        call.respondText("Client inserted successfully")
    }
}