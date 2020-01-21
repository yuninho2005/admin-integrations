package com.crew.api.resource.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AdminIntegrationsApplication

fun main(args: Array<String>) {
	runApplication<AdminIntegrationsApplication>(*args)
}
