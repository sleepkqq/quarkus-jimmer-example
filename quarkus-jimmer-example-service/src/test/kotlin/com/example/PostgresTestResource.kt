package com.example

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.postgresql.PostgreSQLContainer

class PostgresTestResource : QuarkusTestResourceLifecycleManager {

	companion object {
		private val postgres = PostgreSQLContainer("postgres:16.2")
			.withDatabaseName("quarkus_jimmer_test")
			.withUsername("test")
			.withPassword("test")
			.withReuse(true)
	}

	override fun start(): Map<String, String> {
		postgres.start()
		return mapOf(
			"quarkus.datasource.jdbc.url" to postgres.jdbcUrl,
			"quarkus.datasource.username" to postgres.username,
			"quarkus.datasource.password" to postgres.password
		)
	}

	override fun stop() {
		postgres.stop()
	}
}
