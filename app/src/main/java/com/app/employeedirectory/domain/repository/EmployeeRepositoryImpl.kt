package com.app.employeedirectory.domain.repository

import com.app.employeedirectory.core.Resource
import com.app.employeedirectory.core.StringConstants
import com.app.employeedirectory.data.remote.api.EmployeeDirectoryApi
import com.app.employeedirectory.domain.model.Employee
import com.app.employeedirectory.domain.repository.EmployeeValidation.requiredFieldsValidatedMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val api: EmployeeDirectoryApi
): EmployeeRepository {
    // we use a flow builder to return our list of employees wrapped within a Resource wrapper class that will give us the ability to set loading, success and error states.
    override fun getAllEmployees(): Flow<Resource<List<Employee>>> = flow {
        try {
            //first emit a loading state while data is being fetched from the api
            emit(Resource.Loading())
            //fetch data from api
            val employees = api.getAllEmployees().employees.map { it.toEmployee() }.sortedBy { it.fullName }
            //run validation for all employees in list for non-null fields
            employees.forEach { employee ->
                val validationMessage = requiredFieldsValidatedMessage(employee)
                if(validationMessage != "") {
                    throw InvalidObjectException(validationMessage)
                }
            }
            emit(Resource.Success(employees))
        }catch (e: InvalidObjectException){
            emit(Resource.Error(e.message ?: "Json response data has null values!"))
        }catch(e: IOException){
            emit(Resource.Error("A network error occurred. Please check internet connection"))
        } catch(e: Exception){
            emit(Resource.Error("An unexpected error occurred."))
        }
    }
}

object EmployeeValidation{
        fun requiredFieldsValidatedMessage(
            employee: Employee
        ): String{
            return when {
                employee.uuid == null -> StringConstants.EMPLOYEE_UUID_CANNOT_BE_NULL
                employee.fullName == null -> StringConstants.EMPLOYEE_FULL_NAME_CANNOT_BE_NULL
                employee.emailAddress == null -> StringConstants.EMPLOYEE_EMAIL_ADDRESS_CANNOT_BE_NULL
                employee.team == null -> StringConstants.EMPLOYEE_TEAM_CANNOT_BE_NULL
                employee.employeeType == null -> StringConstants.EMPLOYEE_TYPE_CANNOT_BE_NULL
                else -> ""
            }
        }
}