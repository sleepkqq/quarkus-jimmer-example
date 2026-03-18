package com.example.resource

import com.example.model.entity.dto.BookInput
import com.example.model.entity.dto.BookView
import com.example.service.BookService
import io.smallrye.common.annotation.RunOnVirtualThread
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/books")
@RunOnVirtualThread
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BookResource(
	private val bookService: BookService
) {

	@GET
	fun findAll(): List<BookView> =
		bookService.findAll(BookView::class)

	@GET
	@Path("/{id}")
	fun findById(@PathParam("id") id: Long): Response {
		val book = bookService.findById(id, BookView::class)
			?: return Response.status(404).build()
		return Response.ok(book).build()
	}

	@POST
	fun create(input: BookInput): Response {
		val saved = bookService.create(input, BookView::class)
		return Response.status(201).entity(saved).build()
	}

	@PUT
	@Path("/{id}")
	fun update(@PathParam("id") id: Long, input: BookInput): Response {
		val saved = bookService.update(id, input, BookView::class)
		return Response.ok(saved).build()
	}

	@DELETE
	@Path("/{id}")
	fun delete(@PathParam("id") id: Long): Response {
		bookService.deleteById(id)
		return Response.noContent().build()
	}
}
