package com.demo.crudKotlin.service

import com.demo.crudKotlin.dto.EmployeeDTO
import com.demo.crudKotlin.entity.Employee


interface EmployeeService {

    fun createEmp(employee: EmployeeDTO): EmployeeDTO
    fun findById(id: Int): EmployeeDTO
    fun updateEmp(employee: EmployeeDTO): EmployeeDTO
    fun deleteEmpById(id: Int): String
    fun getAllEmp(): List<EmployeeDTO>
}