package com.demo.crudKotlin.entity

import jakarta.persistence.*

@Entity
@Table(name = "employee_details")
data class Employee(
    @Id
    @GeneratedValue
    val id: Int = 0,
    var name: String = "",
    var designation: String = "",
    var mobile: String = "",
    var email: String =""
)