package com.kangdroid.nginxupdater.controller

import com.kangdroid.nginxupdater.service.UpdaterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class MainAPIController(
    private val updaterService: UpdaterService
) {
    @PostMapping("/main/file")
    fun updateServerRequest(@RequestPart("uploadFile") file: MultipartFile) {
        updaterService.updateServer(file)
    }
}