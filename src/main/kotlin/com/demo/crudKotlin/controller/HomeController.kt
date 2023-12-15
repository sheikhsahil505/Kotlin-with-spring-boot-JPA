package com.demo.crudKotlin.controller

import com.demo.crudKotlin.dto.EmployeeDTO
import com.demo.crudKotlin.service.EmployeeService
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
 class HomeController(val employeeService: EmployeeService) {

    @PostMapping("/create")
    fun createEmp(@RequestBody employee: EmployeeDTO): EmployeeDTO {
        return employeeService.createEmp(employee)
    }

    @GetMapping("/{empId}")
    fun findEmp(@PathVariable("empId") id: Int): ResponseEntity<Any> {
        return try {
            val findById = employeeService.findById(id)
            ResponseEntity.ok(findById)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: $id")
        }
    }

    @PutMapping("/update")
    fun updateEmp(@RequestBody employee: EmployeeDTO): ResponseEntity<Any> {
        return try {
            val updatedEmployee = employeeService.updateEmp(employee)
            ResponseEntity.ok(updatedEmployee)
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: ${employee.id}")
        }
    }

    @DeleteMapping("/{empId}")
    fun deleteEmp(@PathVariable("empId") id: Int): String {
        return employeeService.deleteEmpById(id)
    }

    @GetMapping("findAll")
    fun findAll(): List<EmployeeDTO> {
        return employeeService.getAllEmp()
    }
}