package com.navi.serverupdater.serverupdater.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@SpringBootTest
@RunWith(SpringRunner::class)
class ServerControllerServiceTest {
    @Autowired
    private lateinit var serverControllerService: ServerControllerService

    @Test
    fun isStartingStoppingServerOk() {
        val serverFileDirectory: File = File(System.getProperty("java.io.tmpdir"), "testServer.jar")
        val serverURL: String =
            "https://github.com/Navi-Cloud/Navi-Server/releases/download/V0.5.0-Alpha/NavIServer-1.0-SNAPSHOT.jar"

        println("Downloading files...")
        URL(serverURL).openStream().use { `in` ->
            val serverPath: Path = Paths.get(serverFileDirectory.absolutePath)
            Files.copy(`in`, serverPath)
        }
        println("Download Finished!")

        serverControllerService.startServer(serverFileDirectory)

        assertThat(serverControllerService.stopServer()).isEqualTo(false)

        // cleanup
        if (serverFileDirectory.exists()) {
            serverFileDirectory.delete()
        }
    }
}