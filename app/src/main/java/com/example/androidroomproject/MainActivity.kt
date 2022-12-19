package com.example.androidroomproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.androidroomproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainVM by viewModels()

    companion object {
        lateinit var context: WeakReference<Context>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = WeakReference(this)
        binding.viewModel = viewModel
        viewModel.navController = findNavController(R.id.fragmentMain)
    }
}