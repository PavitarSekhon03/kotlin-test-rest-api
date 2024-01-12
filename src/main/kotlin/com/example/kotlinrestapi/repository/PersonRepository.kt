package com.example.kotlinrestapi.repository

import com.example.kotlinrestapi.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long>{
    fun findByNameContainingAndAge(name: String?, age: Int?): List<Person>
}