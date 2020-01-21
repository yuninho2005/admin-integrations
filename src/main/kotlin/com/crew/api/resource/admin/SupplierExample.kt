package com.crew.api.resource.admin

import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class SupplierExample: Supplier<Map<String, Any>> {
    override fun get(): Map<String, Any> {
        return  mapOf(
            "email" to "prueba@gmail.com",
            "password" to "P@ssw0rd"
        )
    }
}