package com.crew.core.service.admin.integrations.tacobell

import com.amazonaws.services.s3.AmazonS3Client
import com.fasterxml.jackson.core.type.TypeReference
import org.apache.logging.log4j.LogManager
import java.text.SimpleDateFormat
import java.util.Date

class TacoBellConfigHelper(
    private val amazonS3Client: AmazonS3Client,
    private val s3WorkingBucket: String
) {
    companion object {
        private val LOG = LogManager.getLogger(TacoBellConfigHelper::class.java)
        private const val TB_S3_LOCATION_FILE_PREFIX = "taco-bell/locations/"
    }

    fun getTacoBellLocationFileForEnterprise(enterpriseId: String): Map<String, String> {
        return emptyMap()
    }

    fun backupLocations(userId: String, enterpriseId: String, updatedAt: Date, locations: Map<String, Any>) {
    }

    fun saveNewLocations(enterpriseId: String, locations: Map<String, Any>) {
    }
}

private data class BackupContent(
    val previousLocations: Map<String, Any>,
    val userId: String,
    val updatedAt: Date
)