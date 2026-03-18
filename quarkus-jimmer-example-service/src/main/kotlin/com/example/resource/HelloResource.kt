package com.example.resource

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import io.smallrye.common.annotation.RunOnVirtualThread

@Path("/hello")
@RunOnVirtualThread
class HelloResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	fun hello(): String = "Hello from Quarkus!"

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	fun helloJson(): Map<String, String> = mapOf(
		"message" to "Hello from Quarkus!",
		"framework" to "Quarkus",
		"language" to "Kotlin"
	)
}
