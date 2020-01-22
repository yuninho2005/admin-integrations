package com.crew.core.service.admin

import com.crew.core.service.admin.integrations.tacobell.TacoBellConfigHelper
import org.apache.logging.log4j.LogManager
import java.time.ZoneId
import java.util.Date

class AdminIntegrationsService(
    private val helper: TacoBellConfigHelper
) {
    companion object {
        private val LOG = LogManager.getLogger(AdminIntegrationsService::class.java)
    }

    fun getTacoBellLocationFileForEnterprise(enterpriseId: String): Map<String, String> {
        return  mutableMapOf(
            "1" to "Australia/Darwin",
            "2" to "Australia/Sydney",
            "3" to "America/Argentina/Buenos_Aires"
        )
    }

    fun updateTacoBellLocationFileForEnterprise(userId: String, enterpriseId: String, locations: Map<String, String>): Map<String, Any> {
        try {
            val locationMap = mutableMapOf<String, String>()

            locations.map { locationMap[it.key] = ZoneId.of(it.value).toString() }

            val existingLocations = mutableMapOf(
                "1" to "Australia/Darwin",
                "2" to "Australia/Sydney",
                "3" to "America/Argentina/Buenos_Aires"
            )

            helper.backupLocations(userId, enterpriseId, Date(), existingLocations)

            existingLocations.map { locationMap[it.key] = ZoneId.of(it.value).toString() }
            helper.saveNewLocations(enterpriseId, locationMap)

            return locationMap
        } catch (e: Exception) {
            LOG.error("Error while updating locations", e)
        }

        return emptyMap()
    }
}