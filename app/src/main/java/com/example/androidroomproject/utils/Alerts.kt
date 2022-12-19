package com.example.androidroomproject.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.example.androidroomproject.MainActivity
import com.example.androidroomproject.databinding.ProgressLayoutBinding
import com.google.android.material.snackbar.Snackbar

object Alerts {

    /**Alert*/
    fun showMessage(
        message: String?,
        actionText: String? = null,
        listener: View.OnClickListener? = null
    ) {
        if (message.isNullOrBlank())
            return
        val snackbar = Snackbar.make(
            (MainActivity.context.get() as Activity).findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        )
        if (!actionText.isNullOrBlank() && listener != null)
            snackbar.setAction(actionText, listener)
        snackbar.show()
        snackbar.duration
    }

    /**Loader*/
    private var customDialog: AlertDialog? = null
    fun showProgress() {
        hideProgress()
        val customAlertBuilder = AlertDialog.Builder(MainActivity.context.get())
        val customAlertView =
            ProgressLayoutBinding.inflate(
                LayoutInflater.from(MainActivity.context.get()),
                null,
                false
            )
        customAlertBuilder.setView(customAlertView.root)
        customAlertBuilder.setCancelable(false)
        customDialog = customAlertBuilder.create()


        customDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog?.show()
    }

    fun hideProgress() {
        if (customDialog != null && customDialog?.isShowing == true) {
            customDialog?.dismiss()
        }
    }
}