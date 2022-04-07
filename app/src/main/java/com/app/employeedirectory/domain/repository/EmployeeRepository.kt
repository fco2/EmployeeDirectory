package com.app.employeedirectory.domain.repository

import com.app.employeedirectory.core.Resource
import com.app.employeedirectory.domain.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    fun getAllEmployees(): Flow<Resource<List<Employee>>>
}