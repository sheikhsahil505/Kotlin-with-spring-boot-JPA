package com.demo.crudKotlin.service.impl


import com.demo.crudKotlin.dto.EmployeeDTO
import com.demo.crudKotlin.entity.Employee
import com.demo.crudKotlin.exception.UserNotFoundException
import com.demo.crudKotlin.repository.EmployeeRepository
import com.demo.crudKotlin.service.EmployeeService
import jakarta.persistence.EntityNotFoundException
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(val employeeRepo: EmployeeRepository) : EmployeeService {

    override fun createEmp(employeeDTO: EmployeeDTO): EmployeeDTO {
        val empEntity = mapDtoToEntity(employeeDTO)
        val savedEmpEntity = employeeRepo.save(empEntity)
        return mapEntityToDto(savedEmpEntity)
    }


    override fun findById(id: Int): EmployeeDTO {
        val orElse = employeeRepo.findById(id).orElseThrow { UserNotFoundException("Employee with id $id not found") }
        return mapEntityToDto(orElse)
    }

    override fun updateEmp(employee: EmployeeDTO): EmployeeDTO {
        val id = employee.id
        val existingEmployee = employeeRepo.findById(id)
            .orElseThrow { UserNotFoundException("Employee with id $id not found") }
        existingEmployee.name = employee.name
        existingEmployee.designation = employee.designation
        existingEmployee.mobile = employee.mobile
        existingEmployee.email = employee.email
        val save = employeeRepo.save(existingEmployee)
        return mapEntityToDto(save)
    }

    override fun deleteEmpById(id: Int): String {
        val findById = employeeRepo.findById(id)
        var msg: String = if (!findById.isEmpty) {
            employeeRepo.deleteById(id)
            "Deleted $id Successfully"
        } else {
            "user $id not found"
        }
        return msg
    }

    override fun getAllEmp(): List<EmployeeDTO> {
        val findAll = employeeRepo.findAll()
        return findAll.map {
            mapEntityToDto(it)
        }
    }

    fun mapDtoToEntity(employeeDTO: EmployeeDTO): Employee {
        return Employee(
            id = employeeDTO.id,
            name = employeeDTO.name,
            designation = employeeDTO.designation,
            mobile = employeeDTO.mobile,
            email = employeeDTO.email
        )
    }

     fun mapEntityToDto(employee: Employee): EmployeeDTO {
        return EmployeeDTO(
            id = employee.id,
            name = employee.name,
            designation = employee.designation,
            mobile = employee.mobile,
            email = employee.email
        )
    }
}