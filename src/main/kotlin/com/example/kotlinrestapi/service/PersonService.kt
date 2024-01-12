package com.example.kotlinrestapi.service

import com.example.kotlinrestapi.entity.Person
import com.example.kotlinrestapi.repository.PersonRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service

@Service
@Transactional
class PersonService(private val personRepository: PersonRepository) {

    fun getAllPersons(pageable: Pageable): Page<Person> {
        return personRepository.findAll(pageable)
    }

    fun createPerson(person: Person): Person {
        return personRepository.save(person)
    }

    fun getPersonById(id: Long): Person {
        return personRepository.findById(id).orElseThrow{throw RuntimeException("Person not found with id $id")}
    }

    fun updatePerson(id:Long, updatedPerson: Person): Person {
        if (!personRepository.existsById(id)) {
            throw RuntimeException("Person not found with id $id")
        }
        updatedPerson.id = id
        return personRepository.save(updatedPerson)
    }

    fun deletePerson(id: Long) {
        if (!personRepository.existsById(id)) {
            throw RuntimeException("Person not found with id $id")
        }
        personRepository.deleteById(id)
    }

    fun filterPersons(name: String?, age: Int?): List<Person> {
        return personRepository.findByNameContainingAndAge(name, age)
    }
}