package org.example.API

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.Domain.Client
import org.example.Domain.ClientRequest
import org.example.Service.ClientService


fun Route.clientAPI() {
    val service = ClientService()

    post("/client") {
        val request = call.receive<ClientRequest>()

        val id = service.register(Client(request))

        call.respond(message = id as Any, status = io.ktor.http.HttpStatusCode.OK)
    }

    get("/client") {
        val ret = service.getAll()
        val encoded = Json.encodeToString(ret)
        call.respond(message = encoded, status = io.ktor.http.HttpStatusCode.OK)
    }

}
