package com.sk.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SKApplication

fun main(args: Array<String>) {
	runApplication<SKApplication>(*args)
}
