package com.app.employeedirectory.presentation.employee_details

import com.app.employeedirectory.domain.model.Employee

data class EmployeeDetailState(
    val employee: Employee? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
