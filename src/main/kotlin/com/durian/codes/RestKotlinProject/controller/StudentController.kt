package com.durian.codes.RestKotlinProject.controller

import com.durian.codes.RestKotlinProject.service.StudentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController(private val studentService: StudentService) {
    @GetMapping
    fun getStudentList() = studentService.getStudentList()
}
