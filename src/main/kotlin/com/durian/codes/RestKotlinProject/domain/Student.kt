package com.durian.codes.RestKotlinProject.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Student(
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private val id: Long = 0,

    @Column(name = "student_name")
    val name: String,

    @Column(name = "student_surname")
    val surname: String,

    @Column(name = "student_number")
    val studentNumber: Long
) {
    private val createdAt: LocalDateTime = LocalDateTime.now()
}
