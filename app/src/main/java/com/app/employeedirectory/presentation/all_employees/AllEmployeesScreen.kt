package com.app.employeedirectory.presentation.all_employees

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.employeedirectory.presentation.all_employees.components.EmployeeInfo
import com.app.employeedirectory.ui.theme.LightGreenBackground
import com.app.employeedirectory.ui.theme.SoftGreenBackground
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun AllEmployeesScreen(
    modifier: Modifier = Modifier,
    placeHolderImgId: Int,
    contentDescriptionText: String,
    allEmployeesHeaderText: String,
    emptyListMessage: String,
    viewModel: AllEmployeesViewModel = hiltViewModel()
){
    val allEmployeeState = viewModel.allEmployeesState

    Scaffold(scaffoldState = rememberScaffoldState()) {
        var isCurrentlyRefreshing by remember {mutableStateOf(false) }
        LaunchedEffect(key1 = isCurrentlyRefreshing){
            if(isCurrentlyRefreshing)
                isCurrentlyRefreshing = false // reset the isCurrentlyRefreshing flag after refresh
        }

        Box(modifier = modifier.fillMaxSize())
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(LightGreenBackground))
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(SoftGreenBackground)
                        .padding(start = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = allEmployeesHeaderText,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LightGreenBackground
                    )
                }
                //Wrap list inside swipe refresh
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = isCurrentlyRefreshing),
                    onRefresh = {
                        isCurrentlyRefreshing = true
                        viewModel.instantiateAllEmployeesState()
                    }
                ) {
                    //Lazy Column for list of employees
                    if(allEmployeeState.errorMessage == ""){
                        LazyColumn(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)){
                            itemsIndexed(allEmployeeState.employees){ index,employee ->
                                if(index == 0)
                                    Spacer(modifier = Modifier.padding(6.dp))
                                EmployeeInfo(
                                    employee = employee,
                                    placeHolderImgId = placeHolderImgId,
                                    contentDescriptionText = contentDescriptionText
                                )
                                if(index == allEmployeeState.employees.lastIndex)
                                    Spacer(modifier = Modifier.padding(6.dp))
                            }
                        }
                    }
                }
            }

            //Case: show empty list message if its not loading and list is empty and there were no errors.
            if(!allEmployeeState.isLoading && allEmployeeState.errorMessage == "" && allEmployeeState.employees.isEmpty()){
                Text(
                    text = emptyListMessage,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
            //Case: error encountered during fetch
            if(allEmployeeState.errorMessage != ""){
                Text(
                    text = allEmployeeState.errorMessage,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(18.dp)
                        .align(
                            Alignment.Center
                        )
                )
            }
            if(allEmployeeState.isLoading){
                // Case: loading data from api
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}