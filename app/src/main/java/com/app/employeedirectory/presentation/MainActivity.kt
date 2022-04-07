package com.app.employeedirectory.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.employeedirectory.R
import com.app.employeedirectory.presentation.all_employees.AllEmployeesScreen
import com.app.employeedirectory.presentation.util.Screen
import com.app.employeedirectory.ui.theme.EmployeeDirectoryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeDirectoryTheme {
                val navController = rememberNavController()
                val placeHolderImageId = R.drawable.user_img_placeholder
                val contentDescriptionText = getString(R.string.employee_image)
                val allEmployeesHeaderText = getString(R.string.all_employees_header_text)
                val emptyListMessage = getString(R.string.empty_list_message)

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = Screen.ALL_EMPLOYEES ){
                        composable(route = Screen.ALL_EMPLOYEES){
                            AllEmployeesScreen(
                                placeHolderImgId = placeHolderImageId,
                                contentDescriptionText = contentDescriptionText,
                                allEmployeesHeaderText = allEmployeesHeaderText,
                                emptyListMessage = emptyListMessage
                            )
                        }
                    }
                }
            }
        }
    }
}