package com.durian.codes.RestKotlinProject.service

import com.durian.codes.RestKotlinProject.domain.Student
import com.durian.codes.RestKotlinProject.exception.StudentNotFoundException
import com.durian.codes.RestKotlinProject.repository.StudentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class StudentServiceTest {

    @Mock
    private lateinit var studentRepository: StudentRepository

    @InjectMocks
    private lateinit var studentService: StudentService

    @Test
    fun retrieveStudentList() {
        val students = listOf(
            Student(name = "John", surname = "Cooper", studentNumber = 101),
            Student(name = "Thomas", surname = "Edison", studentNumber = 102),
            Student(name = "Tony", surname = "Hawk", studentNumber = 103)
        )

        `when`(studentRepository.findAll()).thenReturn(students)
        val studentList = studentService.getStudentList()
        assertEquals(3, studentList.size)
        assertEquals("John Cooper", studentList.find { it.studentNumber == 101L }?.studentFullName)
        assertEquals("Thomas Edison", studentList.find { it.studentNumber == 102L }?.studentFullName)
        assertEquals("Tony Hawk", studentList.find { it.studentNumber == 103L }?.studentFullName)
    }

    @Test
    fun retrieveStudentListForEmpty() {
        `when`(studentRepository.findAll()).thenReturn(emptyList())
        val studentList = studentService.getStudentList()
        assertEquals(0, studentList.size)
    }

    @Test
    fun retrieveAStudent() {
        val student = Student(name = "John", surname = "Cooper", studentNumber = 101)
        `when`(studentRepository.findStudentByStudentNumber(101)).thenReturn(Optional.of(student))
        val studentResponse = studentService.getStudent(101L)
        assertEquals("John Cooper", studentResponse.studentFullName)
    }

    @Test
    fun couldNotFindAStudent() {
        assertThrows(StudentNotFoundException::class.java) {
            studentService.getStudent(101L)
        }
    }


}
