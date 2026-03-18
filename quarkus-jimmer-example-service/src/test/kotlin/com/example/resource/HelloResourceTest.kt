package com.example.resource

import com.example.PostgresTestResource
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(PostgresTestResource::class)
class HelloResourceTest {

	@Test
	fun testHelloEndpoint() {
		given()
			.`when`().get("/hello")
			.then()
			.statusCode(200)
			.body(`is`("Hello from Quarkus!"))
	}

	@Test
	fun testHelloJsonEndpoint() {
		given()
			.`when`().get("/hello/json")
			.then()
			.statusCode(200)
			.body("message", `is`("Hello from Quarkus!"))
	}
}
