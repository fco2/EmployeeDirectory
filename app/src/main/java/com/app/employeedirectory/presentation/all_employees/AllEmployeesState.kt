package com.app.employeedirectory.presentation.all_employees

import com.app.employeedirectory.domain.model.Employee

data class AllEmployeesState(
    val isLoading: Boolean = false,
    val employees: List<Employee> = emptyList(),
    val errorMessage: String = ""
)
