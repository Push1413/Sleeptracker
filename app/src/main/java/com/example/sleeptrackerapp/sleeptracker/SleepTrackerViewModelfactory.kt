package com.example.sleeptrackerapp.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackerapp.database.SleepDatabaseDAO
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SleepTrackerViewModelfactory (
    private val datasource:SleepDatabaseDAO,
    private val application:Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepTrackerViewmodel::class.java)){
            return SleepTrackerViewmodel(datasource,application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}