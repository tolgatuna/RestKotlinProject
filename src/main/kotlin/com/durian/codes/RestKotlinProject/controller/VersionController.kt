package com.durian.codes.RestKotlinProject.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class VersionController {
    @GetMapping("/version")
    fun getVersion() = 1.0
}
