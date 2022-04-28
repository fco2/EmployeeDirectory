package com.app.employeedirectory.data.remote.dto

import com.app.employeedirectory.domain.model.Employee
import com.google.gson.annotations.SerializedName

data class EmployeeDto(
    val biography: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("employee_type")
    val employeeType: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    val team: String,
    val uuid: String
){
    fun toEmployee(): Employee{
        return Employee(
            fullName = fullName,
            phoneNumber = phoneNumber,
            photoUrlSmall = photoUrlSmall,
            team = team,
            uuid = uuid,
            employeeType = employeeType,
            emailAddress = emailAddress,
            biography = biography,
            photoUrlLarge = photoUrlLarge
        )
    }
}
