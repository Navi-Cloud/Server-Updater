package com.kangdroid.nginxupdater.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class UpdaterService {
    // Server Store Location
    private val tempServerFileLocation: File = File("/working/newServer.jar")

    fun updateServer(uploadedFile: MultipartFile) {
        // Store File first.
        reNewServerFile(uploadedFile)

        // Execute Update Script
        val process: Process = Runtime.getRuntime().exec(
            "sh -c \"/working/update.sh\""
        )

        // Wait for finishes
        process.waitFor()
    }

    private fun reNewServerFile(targetFile: MultipartFile) {
        if (tempServerFileLocation.exists()) tempServerFileLocation.delete()
        targetFile.transferTo(tempServerFileLocation)
    }
}