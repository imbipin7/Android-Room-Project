package com.example.androidroomproject.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.androidroomproject.R
import com.example.androidroomproject.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    private var binding: HomeFragmentBinding? = null

    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding?.vm = viewModel
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { demoData ->
            Log.e("TAG", "onClick:Home ==== $demoData")

            viewModel.adapter.addItems(
                demoData
            )
        })
    }
}