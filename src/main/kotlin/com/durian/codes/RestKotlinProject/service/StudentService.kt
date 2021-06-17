package com.durian.codes.RestKotlinProject.service

import com.durian.codes.RestKotlinProject.exception.StudentNotFoundException
import com.durian.codes.RestKotlinProject.message.response.StudentResponse
import com.durian.codes.RestKotlinProject.message.response.fromStudent
import com.durian.codes.RestKotlinProject.repository.StudentRepository
import com.durian.codes.RestKotlinProject.repository.getStudent
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun getStudentList() =
        studentRepository.findAll().map { fromStudent(it) }

    fun getStudent(studentNumber: Long): StudentResponse =
        studentRepository.getStudent(studentNumber).orElseThrow { StudentNotFoundException() }.let { fromStudent(it) }
}
