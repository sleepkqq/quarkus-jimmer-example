package com.example.resource

import com.example.model.entity.dto.AuthorView
import com.example.service.AuthorService
import io.smallrye.common.annotation.RunOnVirtualThread
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/authors")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
class AuthorResource(
	private val authorService: AuthorService
) {

	@GET
	fun findAll(): List<AuthorView> =
		authorService.findAll(AuthorView::class)

	@GET
	@Path("/{id}")
	fun findById(@PathParam("id") id: Long): Response {
		val author = authorService.findById(id, AuthorView::class)
			?: return Response.status(404).build()
		return Response.ok(author).build()
	}
}
