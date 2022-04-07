package com.app.employeedirectory.data.remote.api

import com.app.employeedirectory.data.remote.dto.AllEmployeesDto
import retrofit2.http.GET

interface EmployeeDirectoryApi {
    @GET(EMPLOYEES)
    suspend fun getAllEmployees(): AllEmployeesDto

    companion object{
        const val BASE_URL = "https://s3.amazonaws.com/"
        //For testing
        const val MALFORMED_EMPLOYEES = "/sq-mobile-interview/employees_malformed.json"
        const val EMPLOYEES = "/sq-mobile-interview/employees.json"
        const val EMPLOYEES_EMPTY = "/sq-mobile-interview/employees_empty.json"
    }
}