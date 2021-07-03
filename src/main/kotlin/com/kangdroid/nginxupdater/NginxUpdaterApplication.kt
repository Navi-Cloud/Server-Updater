package com.kangdroid.nginxupdater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NginxUpdaterApplication

fun main(args: Array<String>) {
	runApplication<NginxUpdaterApplication>(*args)
}
