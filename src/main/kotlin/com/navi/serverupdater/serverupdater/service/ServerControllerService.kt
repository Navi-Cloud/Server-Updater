package com.navi.serverupdater.serverupdater.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

@Service
class ServerControllerService {
    private var serverDirectory: File = File(System.getProperty("user.home"), "NaviServer").also {
        it.mkdir()
    }
    private val destination: File = File(serverDirectory, "testServer.jar")
    private var process: Process? = null
    private val serverSettings: File = File(System.getProperty("java.io.tmpdir"), "application-test.properties")

    fun updateServer(file: MultipartFile) {
        // First we need to stop server.
        if (process != null) {
            if (!stopServer()) {
                println("Server Stopped Successfully")
            } else {
                println("Server Did not stopped or did not started at all!")
            }
        }

        if (moveFiles(file)) {
            println("Successfully moved file!")
        }

        // Start Server
        startServer(destination)
    }

    fun stopServer(): Boolean {
        process?.destroy()
        if (process?.isAlive == true) {
            process?.waitFor()
        }

        return process?.isAlive ?: true
    }

    fun moveFiles(file: MultipartFile): Boolean {
        file.transferTo(destination)

        return destination.exists()
    }

    fun startServer(serverFileDirectory: File) {
        // Set Server Root
        val serverRoot: String = System.getProperty("java.io.tmpdir")
        // Set Property file
        val propertyString: String = """
         spring.jpa.show-sql=true
         spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
         spring.h2.console.enabled=true
         navi.server-root=${serverRoot}
         server.port=8085
     """.trimIndent()
        if (serverSettings.exists()) serverSettings.delete()
        val writer: BufferedWriter = BufferedWriter(FileWriter(serverSettings))
        writer.write(propertyString)
        writer.close()

        val command: Array<String> = arrayOf(
            "java",
            "-jar",
            serverFileDirectory.absolutePath,
            "--spring.config.location=${serverSettings.absolutePath}"
        )
        process = Runtime.getRuntime().exec(command)
    }
}