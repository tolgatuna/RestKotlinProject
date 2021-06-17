package com.durian.codes.RestKotlinProject.acceptance.glue

import com.durian.codes.RestKotlinProject.domain.Student
import com.durian.codes.RestKotlinProject.repository.StudentRepository
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.hamcrest.Matchers.hasLength
import org.hamcrest.Matchers.hasValue


class StudentControllerStepDefinition(private val studentRepository: StudentRepository) {
    private lateinit var response: Response

    @Given("There are students have been already saved to database")
    fun there_are_students_have_been_already_saved_to_database() {
        val student1 = Student(name = "Jack", surname = "Sparrow", studentNumber = 321)
        val student2 = Student(name = "Tom", surname = "Rich", studentNumber = 322)
        val student3 = Student(name = "Daniel", surname = "Elvis", studentNumber = 323)

        studentRepository.save(student1)
        studentRepository.save(student2)
        studentRepository.save(student3)
    }

    @When("the client makes call to student list service")
    fun the_client_makes_call_to_student_list_service() {
        response = given().get("/students")
    }

    @Then("the client should receive student list")
    fun the_client_should_receive_student_list() {
        response.then()
            .statusCode(200)
            .body("$", hasLength(3))
    }

    @When("the client makes call to get student service with student number")
    fun the_client_makes_call_to_get_student_service_with_student_number() {
        response = given().get("/students/{number}", 321)
    }

    @Then("the client should receive requested student")
    fun the_client_should_receive_requested_student() {
        response.then()
            .statusCode(200)
            .body("$.studentName", hasValue("Jack Sparrow"))
    }

    @When("the client makes call to create student service with correct request")
    fun the_client_makes_call_to_create_student_service_with_correct_request() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the client should successfully create a student")
    fun the_client_should_successfully_create_a_student() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the client makes call to update student service with student number")
    fun the_client_makes_call_to_update_student_service_with_student_number() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the client should update student")
    fun the_client_should_update_student() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the client makes call to delete student service with student number")
    fun the_client_makes_call_to_delete_student_service_with_student_number() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the client should delete specified student")
    fun the_client_should_delete_specified_student() {
        // Write code here that turns the phrase above into concrete actions
    }
}
