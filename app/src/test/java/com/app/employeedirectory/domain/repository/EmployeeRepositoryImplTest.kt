package com.app.employeedirectory.domain.repository

import com.app.employeedirectory.core.Resource
import com.app.employeedirectory.core.StringConstants
import com.app.employeedirectory.data.remote.api.EmployeeDirectoryApi
import com.app.employeedirectory.data.remote.dto.AllEmployeesDto
import com.app.employeedirectory.data.remote.dto.EmployeeDto
import com.app.employeedirectory.domain.model.Employee
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class EmployeeRepositoryImplTest {

    private lateinit var validEmployee: EmployeeDto
    private lateinit var inValidEmployee: EmployeeDto
    private val employeeList = mutableListOf<EmployeeDto>()
    private lateinit var repository: EmployeeRepository
    private lateinit var api: EmployeeDirectoryApi

    @Before
    fun setUp() {
        validEmployee = EmployeeDto(
            biography = "First bio",
            emailAddress = "test@gmail.com",
            employeeType = "FULL_TIME",
            fullName = "User 1",
            phoneNumber = "5555555555",
            photoUrlLarge = "xyz.com",
            photoUrlSmall = "abc.com",
            team = "Manager",
            uuid = "1Azkpty"
        )
        inValidEmployee = EmployeeDto(
            biography = "Bio",
            emailAddress = "test@gmail.com",
            employeeType = "FULL_TIME",
            fullName = "User 1",
            phoneNumber = "5555555555",
            photoUrlLarge = "xyz.com",
            photoUrlSmall = "abc.com",
            team = "Manager",
            uuid = null)
        employeeList.add(validEmployee)
        api = mock()
        repository = EmployeeRepositoryImpl(api)
    }

    @Test
    fun `with valid employee in employeeList, should return that employee list`() = runBlocking{
        whenever(api.getAllEmployees()) doReturn AllEmployeesDto(employeeList)

        val flow: Flow<Resource<List<Employee>>> = repository.getAllEmployees()
        flow.collectLatest { result ->
            delay(1000L) // added this delay of 1 second which would allow the Resource.Loading() to finish emitting so we can access the result of Result.Success()
            assertThat(result.data).isEqualTo(employeeList.map { it.toEmployee() })
        }
    }

    @Test
    fun `with null employee uuid in employeeList, should return correct exception message`() = runBlocking {
        employeeList.add(inValidEmployee)
        whenever(api.getAllEmployees()) doReturn AllEmployeesDto(employeeList)
        val flow: Flow<Resource<List<Employee>>> = repository.getAllEmployees()
        flow.collectLatest { result ->
            delay(1000L)
            assertThat(result.message).isEqualTo(StringConstants.EMPLOYEE_UUID_CANNOT_BE_NULL)
        }
    }
}