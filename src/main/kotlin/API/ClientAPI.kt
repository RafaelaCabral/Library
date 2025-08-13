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

    get("/client/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(message="Missing ID", status= io.ktor.http.HttpStatusCode.BadRequest)
            return@get
        }
        val client = service.getById(id)
        if (client != null) {
            call.respond(io.ktor.http.HttpStatusCode.OK, client)
        } else {
            call.respond(message= "Client not found", status=io.ktor.http.HttpStatusCode.NotFound)
        }
    }

    put("/client/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(message="Missing ID", status=io.ktor.http.HttpStatusCode.BadRequest)
            return@put
        }
        val request = call.receive<ClientRequest>()
        val updated = service.update(id, Client(request))
        if (updated) {
            call.respond(message="Client updated successfully", status=io.ktor.http.HttpStatusCode.OK)
        } else {
            call.respond(message="Client not found or not updated", status=io.ktor.http.HttpStatusCode.NotFound)
        }
    }

    delete("/client/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(message="Missing ID", status = io.ktor.http.HttpStatusCode.BadRequest)
            return@delete
        }
        val deleted = service.delete(id)
        if (deleted) {
            call.respond(message="Client deleted successfully", status=io.ktor.http.HttpStatusCode.OK)
        } else {
            call.respond(message="Client not found", status=io.ktor.http.HttpStatusCode.NotFound)
        }
    }


}
