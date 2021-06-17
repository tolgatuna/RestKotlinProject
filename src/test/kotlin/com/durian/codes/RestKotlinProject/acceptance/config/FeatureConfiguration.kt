package com.durian.codes.RestKotlinProject.acceptance.config

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.spring.CucumberContextConfiguration
import io.restassured.RestAssured
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
class FeatureConfiguration(@LocalServerPort val SERVER_PORT: Int) {
    @After
    fun clean() = RestAssured.reset()

    @Before
    fun init() = run {
        print("PORT SELECTED: $SERVER_PORT")
        RestAssured.port = SERVER_PORT
    }
}
