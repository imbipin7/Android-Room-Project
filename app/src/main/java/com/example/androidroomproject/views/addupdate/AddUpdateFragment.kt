package com.example.androidroomproject.views.addupdate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.androidroomproject.R
import com.example.androidroomproject.databinding.AddUpdateFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUpdateFragment : Fragment(R.layout.add_update_fragment) {

    private var binding: AddUpdateFragmentBinding? = null
    private val viewModel: AddUpdateVM by viewModels()
    private val args by navArgs<AddUpdateFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddUpdateFragmentBinding.bind(view)
        binding?.vm = viewModel

        if (args.data != null) {
            viewModel.isEdit.set(true)
            viewModel.id.set(args.data?.id ?: 0)
            viewModel.firstName.set(args.data?.firstName ?: "")
            viewModel.lastName.set(args.data?.lastName ?: "")
            viewModel.age.set(args.data?.age ?: "")
        } else {
            viewModel.readAllData.observe(viewLifecycleOwner, Observer { demoData ->
                Log.e("TAG", "onClick: ==== $demoData")
                if (demoData.isNotEmpty())
                viewModel.id.set((demoData[demoData.size - 1].id ?: 0) + 1)
            })
        }
    }

}