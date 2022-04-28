package com.app.employeedirectory.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.employeedirectory.domain.model.Employee

@Entity(tableName = "employee")
data class EmployeeEntity(
    val biography: String,
    @ColumnInfo(name = "email_address")
    val emailAddress: String,
    @ColumnInfo(name = "employee_type")
    val employeeType: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "photo_url_large")
    val photoUrlLarge: String,
    @ColumnInfo(name = "photo_url_small")
    val photoUrlSmall: String,
    val uuid: String,
    val team: String,
    @PrimaryKey val id: Long? = null
){
}

fun EmployeeEntity.toEmployee(): Employee {
    return Employee(
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
