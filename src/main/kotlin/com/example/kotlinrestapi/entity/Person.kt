package com.example.kotlinrestapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long=0,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val dateOfBirth: LocalDate,
    val age: Int,
    val username: String,
    val password: String
)
