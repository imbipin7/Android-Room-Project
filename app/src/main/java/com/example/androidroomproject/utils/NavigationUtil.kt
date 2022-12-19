package com.example.androidroomproject.utils

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.androidroomproject.MainActivity
import com.example.androidroomproject.validations.ValidatorUtils.hideSoftKeyboard

interface NavigationListener {
    fun isLockDrawer(isLock: Boolean)
    fun openDrawer()
}

/**Navigate using destination ID*/
fun View.navigateWithId(id: Int, bundle: Bundle? = null) = try {
    hideSoftKeyboard(MainActivity.context.get() as Activity)
    this.findNavController().navigate(id, bundle)
} catch (e: Exception) {
    e.printStackTrace()
}


/** Navigate to previous screen*/
fun View.navigateBack() = try {
    hideSoftKeyboard(MainActivity.context.get() as Activity)
    this.findNavController().navigateUp()
} catch (e: Exception) {
    e.printStackTrace()
}