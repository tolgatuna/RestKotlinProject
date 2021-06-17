package com.durian.codes.RestKotlinProject.message.response

import com.durian.codes.RestKotlinProject.domain.Student

data class StudentResponse(val studentFullName: String, val studentNumber: Long)

fun fromStudent(s: Student) = StudentResponse("${s.name} ${s.surname}", s.studentNumber)
