package com.example.kotlinrestapi.controller

import com.example.kotlinrestapi.entity.Person
import com.example.kotlinrestapi.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/persons")
class PersonController (private val personService: PersonService) {

    @GetMapping

    fun getAllPersons(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<Person>> {
        val pageable = PageRequest.of(page, size)
        val persons = personService.getAllPersons(pageable)
        return ResponseEntity.ok(persons)
    }

    @PostMapping()
    fun createPerson(@RequestBody person: Person): ResponseEntity<Person> {
        val person = personService.createPerson(person)
        return ResponseEntity.ok(person)
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable id: Long): ResponseEntity<Person> {
        val person = personService.getPersonById(id)
        return ResponseEntity.ok(person)
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Long, @RequestBody updatedPerson: Person): ResponseEntity<Person> {
        val person = personService.updatePerson(id, updatedPerson)
        return ResponseEntity.ok(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity(personService.deletePerson(id), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/filter")
    fun filterPersons(
        @RequestParam(required = false) name:String?,
        @RequestParam(required = false) age:Int?
    ): ResponseEntity<List<Person>> {
        val filteredResult = personService.filterPersons(name, age)
        return ResponseEntity.ok(filteredResult)
    }


}