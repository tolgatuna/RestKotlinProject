package com.durian.codes.RestKotlinProject.acceptance.glue

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.equalTo

class VersionControllerStepDefinition {
    private lateinit var response: Response

    @When("the client calls version")
    fun the_client_calls_version() {
        response = given().get("/version")
    }

    @Then("the client receives status code of {int}")
    fun the_client_receives_status_code_of(statusCode: Int) {
        response.then()
            .assertThat()
            .statusCode(statusCode)
    }

    @Then("the client receives server version {string}")
    fun the_client_receives_server_version(versionNumber: String?) {
        response
            .then()
            .body(equalTo(versionNumber))
    }
}
