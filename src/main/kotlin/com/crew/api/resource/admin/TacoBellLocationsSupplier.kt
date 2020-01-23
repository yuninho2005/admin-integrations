package com.crew.api.resource.admin

import com.crew.core.service.admin.AdminIntegrationsService
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class TacoBellLocationsSupplier(
    private val adminIntegrationsService: AdminIntegrationsService
): Function<String, Map<String, Any>> {

    override fun apply(enterpriseId: String): Map<String, Any> {
        return  adminIntegrationsService.getTacoBellLocationFileForEnterprise(enterpriseId)
    }
}