package com.app.employeedirectory.presentation.employee_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest


@Composable
fun EmployeeDetailScreen(
    modifier: Modifier = Modifier,
    placeHolderImgId: Int,
    contentDescriptionText: String,
    viewModel: EmployeeDetailViewModel = hiltViewModel()
){
    val state = viewModel.employeeDetailState
    //val employee = state.employee!!
    val context = LocalContext.current

    if(state.employee != null){
        Column(modifier = modifier
            .fillMaxSize()
            .padding(10.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(state.employee.photoUrlSmall)
                    .error(placeHolderImgId)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(state.employee.photoUrlSmall)
                    .build(),
                contentDescription = contentDescriptionText,
                placeholder = painterResource(id = placeHolderImgId),
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = state.employee.fullName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = state.employee.biography, fontSize = 14.sp, fontWeight = FontWeight.Normal)

        }
    }
}