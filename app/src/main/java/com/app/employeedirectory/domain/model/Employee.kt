package com.app.employeedirectory.domain.model

data class Employee(
    val emailAddress: String?,
    val employeeType: String?,
    val fullName: String?,
    val phoneNumber: String?,
    val photoUrlSmall: String?,
    val team: String?,
    val uuid: String?
)