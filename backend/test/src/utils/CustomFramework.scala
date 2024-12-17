package utils

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.BindMode

class CustomFramework extends utest.runner.Framework {
  var postgres: Option[PostgreSQLContainer[?]] = None

  override def setup(): Unit = {
    val container = new PostgreSQLContainer("postgres:latest")
    container.setPortBindings(java.util.List.of("5436:5432"))
    container.start()
    postgres = Some(container)
  }

  override def teardown(): Unit = {
    postgres.map(_.stop())
  }
}

