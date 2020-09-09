package com.example.sleepequality.sleepequality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.sleeptrackerapp.R
import com.example.sleeptrackerapp.databinding.FragmentSleepequalityBinding


class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentSleepequalityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleepequality,container,false)
        return binding.root
    }

}