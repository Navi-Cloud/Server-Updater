package com.navi.serverupdater.serverupdater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerUpdaterApplication

fun main(args: Array<String>) {
	runApplication<ServerUpdaterApplication>(*args)
}
