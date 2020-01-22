package com.crew.api.resource.admin

import org.springframework.stereotype.Component
import java.util.function.Consumer
import java.util.function.Supplier

@Component
class ConsumerExample: Consumer<Map<String, Any>> {
    override fun accept(t: Map<String, Any>) {
        println(t)
    }
}