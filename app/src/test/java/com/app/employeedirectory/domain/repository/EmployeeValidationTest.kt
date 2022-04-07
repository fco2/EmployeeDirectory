package com.app.employeedirectory.domain.repository

import com.app.employeedirectory.core.StringConstants
import com.app.employeedirectory.domain.model.Employee
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class EmployeeValidationTest{

    private lateinit var validEmployee: Employee
    private lateinit var inValidEmployee: Employee

    @Before
    fun setUp(){
        validEmployee = Employee(
            emailAddress = "test@gmail.com",
            employeeType = "FULL_TIME",
            fullName = "User 1",
            phoneNumber = "5555555555",
            photoUrlSmall = "abc.com",
            team = "Manager",
            uuid = "1Azkpty"
        )
        inValidEmployee = Employee(
            emailAddress = "test@gmail.com",
            employeeType = "FULL_TIME",
            fullName = "User 1",
            phoneNumber = "5555555555",
            photoUrlSmall = "abc.com",
            team = "Manager",
            uuid = null)
    }

    @Test
    fun `with valid employee object, returns empty string error message`(){
        val message = EmployeeValidation.requiredFieldsValidatedMessage(validEmployee)
        assertThat(message).isEmpty()
    }

    @Test
    fun `with null employee uuid object, returns correct error message`(){
        val message = EmployeeValidation.requiredFieldsValidatedMessage(inValidEmployee)
        assertThat(message).isEqualTo(StringConstants.EMPLOYEE_UUID_CANNOT_BE_NULL)
    }
}