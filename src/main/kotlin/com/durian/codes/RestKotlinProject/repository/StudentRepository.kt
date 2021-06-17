package com.durian.codes.RestKotlinProject.repository

import com.durian.codes.RestKotlinProject.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    fun findStudentByStudentNumber(studentNumber: Long): Optional<Student>
}

fun StudentRepository.getStudent(studentNumber: Long) = findStudentByStudentNumber(studentNumber)
