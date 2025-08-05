package org.example.Domain

import kotlinx.serialization.Serializable

@Serializable
class Client (
    val client_name: String,
    val client_email: String,
    val client_phone: String
)