package com.app.employeedirectory.presentation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.lang.IllegalArgumentException

class ConstantsTest{

    @Test
    fun `With invalid job type, throws IllegalArgumentException`(){
        val invalidJobType = "ANY_TIME"
        try {
            Constants.prettyPrintJobType(invalidJobType)
        }catch (e: IllegalArgumentException){
            assertThat(e).hasMessageThat().isEqualTo("This is not an accepted job type.")
        }
    }

    @Test
    fun `With a valid job type of FULL_TIME, returns the text Full-time`(){
        val fullTimeJobType = "FULL_TIME"
        assertThat(Constants.prettyPrintJobType(fullTimeJobType)).isEqualTo("Full-time")
    }

    @Test
    fun `With a valid job type of PART_TIME, does NOT return the text Contractor`(){
        val partTimeJobType = "PART_TIME"
        assertThat(Constants.prettyPrintJobType(partTimeJobType)).isNotEqualTo("Contractor")
    }
}