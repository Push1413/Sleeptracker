package com.example.sleeptrackerapp.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleeptrackerapp.database.SleepDatabaseDAO
import kotlinx.coroutines.launch

class SleepQualityViewModel(private val sleepNightKey: Long = 0L,
                            val database: SleepDatabaseDAO
):ViewModel() {
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    /**
     *
     */

    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */
    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    /**
     * Sets the sleep quality and updates the database.
     *
     * Then navigates back to the SleepTrackerFragment.
     */
    fun onSetSleepQuality(quality: Int) {
        viewModelScope.launch {
            val tonight = database.get(sleepNightKey) ?: return@launch
            tonight.sleepQuality = quality
            database.update(tonight)

            // Setting this state variable to true will alert the observer and trigger navigation.
            _navigateToSleepTracker.value = true
        }
    }
}