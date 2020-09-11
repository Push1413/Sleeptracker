package com.example.sleepequality.sleepequality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptrackerapp.R
import com.example.sleeptrackerapp.database.SleepDatabase
import com.example.sleeptrackerapp.databinding.FragmentSleepequalityBinding
import com.example.sleeptrackerapp.sleepquality.SleepQualityViewModel
import com.example.sleeptrackerapp.sleepquality.SleepQualityViewModelFactory


class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentSleepequalityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleepequality,container,false)


        val application = requireNotNull(this.activity).application

        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory.
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.count, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val sleepQualityViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(SleepQualityViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.sleepQualityViewModel = sleepQualityViewModel

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleeptracker())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                sleepQualityViewModel.doneNavigating()
            }
        })
        return binding.root
    }

}