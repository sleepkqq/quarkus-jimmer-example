package com.example.resource

import com.example.PostgresTestResource
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@QuarkusTest
@QuarkusTestResource(PostgresTestResource::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BookResourceTest {

	companion object {
		private var createdBookId: Long = 0
	}

	@Test
	@Order(1)
	fun `should list all books from initial data`() {
		given()
			.`when`().get("/books")
			.then()
			.statusCode(200)
			.body("size()", greaterThanOrEqualTo(2))
	}

	@Test
	@Order(2)
	fun `should get book by id`() {
		given()
			.`when`().get("/books/1")
			.then()
			.statusCode(200)
			.body("title", `is`("Refactoring"))
			.body("authorId", `is`(1))
	}

	@Test
	@Order(3)
	fun `should return 404 for non-existent book`() {
		given()
			.`when`().get("/books/9999")
			.then()
			.statusCode(404)
	}

	@Test
	@Order(4)
	fun `should create a new book`() {
		createdBookId = given()
			.contentType(ContentType.JSON)
			.body("""{"title": "Domain-Driven Design", "price": 59.99, "authorId": 1}""")
			.`when`().post("/books")
			.then()
			.statusCode(201)
			.body("title", `is`("Domain-Driven Design"))
			.body("price", `is`(59.99f))
			.body("authorId", `is`(1))
			.body("id", notNullValue())
			.extract().jsonPath().getLong("id")
	}

	@Test
	@Order(5)
	fun `should update existing book`() {
		given()
			.contentType(ContentType.JSON)
			.body("""{"title": "Domain-Driven Design 2nd Edition", "price": 69.99, "authorId": 1}""")
			.`when`().put("/books/$createdBookId")
			.then()
			.statusCode(200)
			.body("title", `is`("Domain-Driven Design 2nd Edition"))
			.body("price", `is`(69.99f))
	}

	@Test
	@Order(6)
	fun `should delete book`() {
		given()
			.`when`().delete("/books/$createdBookId")
			.then()
			.statusCode(204)

		given()
			.`when`().get("/books/$createdBookId")
			.then()
			.statusCode(404)
	}
}
