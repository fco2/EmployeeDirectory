package com.app.employeedirectory.presentation.all_employees

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.employeedirectory.core.Resource
import com.app.employeedirectory.domain.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllEmployeesViewModel @Inject constructor(
    private val repository: EmployeeRepository
): ViewModel() {

    var allEmployeesState by mutableStateOf(AllEmployeesState())
        private set

    init{
        instantiateAllEmployeesState()
    }

    fun instantiateAllEmployeesState(){
        repository.getAllEmployees().onEach { result ->
            allEmployeesState = when(result){
                is Resource.Success -> {
                    AllEmployeesState(employees = result.data!!)
                }
                is Resource.Loading -> {
                    AllEmployeesState(isLoading = true)
                }
                is Resource.Error -> {
                    AllEmployeesState(errorMessage = result.message ?: "An unexpected error has occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }
}
