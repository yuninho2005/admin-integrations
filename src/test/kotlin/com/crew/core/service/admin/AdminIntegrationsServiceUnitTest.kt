package com.crew.core.service.admin

import com.crew.core.service.admin.integrations.tacobell.TacoBellConfigHelper
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdminIntegrationsServiceUnitTest {
    private val helper: TacoBellConfigHelper = mockk()
    private val adminIntegrationsService = spyk(AdminIntegrationsService(helper))

    @BeforeEach
    fun setUpMocks() {
        clearAllMocks()
    }

    @Test
    fun `There is an error while getting an object from the given s3 bucket`() {
        every {
            helper.getTacoBellLocationFileForEnterprise("enterpriseId")
        } throws Exception("There was an error getting the s3 object")

        val response = adminIntegrationsService.getTacoBellLocationFileForEnterprise("enterpriseId")

        assertThat(response)
    }

    @Test
    fun `We should get the locations`() {
        every {
            helper.getTacoBellLocationFileForEnterprise("enterpriseId")
        } returns mutableMapOf("3" to "America/Argentina/Buenos_Aires")

        val response = adminIntegrationsService.getTacoBellLocationFileForEnterprise("enterpriseId")

        assertNotNull(response)
    }

    @Test
    fun `When updating locations if there is an invalid one we should get an exception`() {
        val newLocations = mutableMapOf(
            "1" to "Australia/Darwin",
            "2" to "Australia/S"
        )

        val response = adminIntegrationsService.updateTacoBellLocationFileForEnterprise("userId", "enterpriseId", newLocations)
        assertThat(response).isEmpty()
    }

    @Test
    fun `New locations should be merged with existing locations`() {
        mockGetLocations()
        mockS3Management()

        val newLocations = mutableMapOf(
            "1" to "Australia/Darwin",
            "2" to "Australia/Sydney"
        )

        val result = adminIntegrationsService.updateTacoBellLocationFileForEnterprise("userId", "enterpriseId", newLocations)

        assertEquals(3, result.size)
    }

    @Test
    fun `Verify that we backup existing locations and save all the locations into the specific s3 bucket`() {
        mockGetLocations()
        mockS3Management()

        val newLocations = mutableMapOf(
            "1" to "Australia/Darwin",
            "2" to "Australia/Sydney"
        )

        adminIntegrationsService.updateTacoBellLocationFileForEnterprise("userId", "enterpriseId", newLocations)

        verify { helper.backupLocations(any(), any(), any(), any()) }
        verify { helper.saveNewLocations(any(), any()) }
    }

    private fun mockGetLocations() {
        val existingLocations = mutableMapOf(
            "3" to "America/Argentina/Buenos_Aires"
        )

        every {
            helper.getTacoBellLocationFileForEnterprise(any())
        } returns existingLocations
    }

    private fun mockS3Management() {
        every {
            helper.backupLocations(any(), any(), any(), any())
        } returns Unit

        every {
            helper.saveNewLocations(any(), any())
        } returns Unit
    }
}