package com.example.resource

import com.example.PostgresTestResource
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(PostgresTestResource::class)
class AuthorResourceTest {

	@Test
	fun `should list all authors from initial data`() {
		given()
			.`when`().get("/authors")
			.then()
			.statusCode(200)
			.body("size()", greaterThanOrEqualTo(2))
			.body("[0].name", `is`("Martin Fowler"))
	}

	@Test
	fun `should get author by id`() {
		given()
			.`when`().get("/authors/1")
			.then()
			.statusCode(200)
			.body("name", `is`("Martin Fowler"))
			.body("id", `is`(1))
	}

	@Test
	fun `should return 404 for non-existent author`() {
		given()
			.`when`().get("/authors/9999")
			.then()
			.statusCode(404)
	}
}
