package com.app.employeedirectory.presentation.util

import java.lang.IllegalArgumentException

object Constants {
    fun prettyPrintJobType(jobType: String): String{
        return when(jobType){
            "FULL_TIME" -> "Full-time"
            "PART_TIME" -> "Part-time"
            "CONTRACTOR" -> "Contractor"
            else -> throw IllegalArgumentException("This is not an accepted job type.")
        }
    }
}