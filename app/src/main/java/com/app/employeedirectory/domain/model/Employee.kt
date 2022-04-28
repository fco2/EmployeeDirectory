package com.app.employeedirectory.domain.model

import com.app.employeedirectory.data.local.EmployeeEntity

data class Employee(
    val biography: String,
    val emailAddress: String,
    val employeeType: String,
    val fullName: String,
    val phoneNumber: String,
    val photoUrlSmall: String,
    val photoUrlLarge: String,
    val team: String,
    val uuid: String
)

fun Employee.toEmployeeEntity(): EmployeeEntity{
    return EmployeeEntity(
        biography = biography,
        emailAddress = emailAddress,
        employeeType = employeeType,
        fullName = fullName,
        phoneNumber = phoneNumber,
        photoUrlSmall = photoUrlSmall,
        photoUrlLarge = photoUrlLarge,
        uuid = uuid,
        team = team
    )
}