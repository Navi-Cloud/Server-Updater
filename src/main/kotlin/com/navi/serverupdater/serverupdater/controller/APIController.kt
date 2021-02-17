package com.navi.serverupdater.serverupdater.controller

import com.navi.serverupdater.serverupdater.service.ServerControllerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class APIController {

    @Autowired
    private lateinit var serverControllerService: ServerControllerService

    @PostMapping("/api/server")
    fun serverUpdater(@RequestParam file: MultipartFile) {
        serverControllerService.updateServer(file)
    }
}