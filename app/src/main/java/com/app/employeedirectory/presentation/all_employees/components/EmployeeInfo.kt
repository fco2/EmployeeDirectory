package com.app.employeedirectory.presentation.all_employees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.app.employeedirectory.domain.model.Employee
import com.app.employeedirectory.presentation.util.Constants


@Composable
fun EmployeeInfo(
    employee: Employee,
    modifier: Modifier = Modifier,
    placeHolderImgId: Int,
    contentDescriptionText: String,
){
    val context = LocalContext.current

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = context)
            .data(employee.photoUrlSmall)
            .error(placeHolderImgId)
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCacheKey(employee.photoUrlSmall)
            .build(),
            contentDescription = contentDescriptionText,
            placeholder =  painterResource(id = placeHolderImgId),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(start = 5.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(horizontal = 12.dp))
        Column(modifier = Modifier.fillMaxHeight().padding(10.dp)) {
            Text(text = employee.fullName!!, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = employee.team!!, fontSize = 14.sp, fontWeight = FontWeight.Normal)
            Text(text = Constants.prettyPrintJobType(employee.employeeType!!), fontSize = 14.sp, fontWeight = FontWeight.Normal)
            Text(text = employee.emailAddress!!, fontSize = 14.sp, fontWeight = FontWeight.Normal)
            Text(text = employee.phoneNumber!!, fontSize = 14.sp, fontWeight = FontWeight.Normal)
        }

    }
}