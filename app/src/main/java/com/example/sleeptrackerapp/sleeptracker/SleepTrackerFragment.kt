package com.example.sleeptrackerapp.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.sleeptrackerapp.R
import com.example.sleeptrackerapp.database.SleepDatabase
import com.example.sleeptrackerapp.databinding.FragmentSleeptrackerBinding

class SleepTrackerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        // Inflate the layout for this fragment
        val binding: FragmentSleeptrackerBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleeptracker,container,false)


        // Create an instance of the ViewModel Factory.
        val viewModelFactory = SleepTrackerViewModelfactory(dataSource, application)

// Get a reference to the ViewModel associated with this fragment.
        val sleepTrackerViewModel = ViewModelProvider(this,viewModelFactory).get(SleepTrackerViewmodel::class.java)

        binding.setLifecycleOwner(this)


        return binding.root
    }


}