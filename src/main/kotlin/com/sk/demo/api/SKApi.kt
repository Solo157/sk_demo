package com.sk.demo.api

import com.sk.demo.service.SKService
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/modify")
class SKApi(
    val service: SKService
) {

    @PostMapping(produces = ["application/json"])
    fun modifySKExample(@RequestBody request: SKRequest) =
        try {
            ResponseEntity(service.modifySKExample(request), OK)
        } catch (e: Exception) {
            ResponseEntity(I_AM_A_TEAPOT)
        }
}
