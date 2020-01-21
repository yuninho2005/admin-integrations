package com.crew.api.resource.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class AdminIntegrationsApplication

fun main(args: Array<String>) {
	runApplication<AdminIntegrationsApplication>(*args)
}

@Bean
fun kotlinFunction(): (String) -> String {
	return  { it.toUpperCase() }
}

@Bean
fun kotlinConsumer(): (String) -> Unit {
	return  { println(it) }
}