package com.app.employeedirectory.presentation.employee_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.employeedirectory.core.Resource
import com.app.employeedirectory.domain.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EmployeeDetailViewModel @Inject constructor(
    private val repository: EmployeeRepository,
    savedStateHandle: SavedStateHandle
 ): ViewModel() {

    var employeeDetailState by mutableStateOf(EmployeeDetailState())

    init {
        val uuid = savedStateHandle.get<String>("uuid")!!
        getEmployeeDetail(uuid)
    }

    private fun getEmployeeDetail(uuid: String){
        Log.d("Test2", "Test2, uuid ${uuid}")
        repository.getAllEmployees().onEach { result ->
            Log.d("Test2", "Test2, ${result.data}")
            employeeDetailState = when(result){
                is Resource.Success -> {
                    Log.d("Test2", "Test2, success ${result.data}")
                    employeeDetailState.copy(isLoading = false, employee = result.data!!.first { it.uuid == uuid})
                }
                is Resource.Loading -> {
                    Log.d("Test2", "Test2, loading")
                    employeeDetailState.copy(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d("Test2", "Test2, error ")
                    employeeDetailState.copy(isLoading = false, errorMessage = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}