package com.example.androidroomproject.views.addupdate

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.androidroomproject.R
import com.example.androidroomproject.model.DemoModel
import com.example.androidroomproject.repository.UserRepository
import com.example.androidroomproject.validations.Validation
import com.example.androidroomproject.views.db.UserDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUpdateVM @Inject constructor(application: Application) : ViewModel() {

    var firstName: ObservableField<String> = ObservableField()
    var lastName: ObservableField<String> = ObservableField()
    var age: ObservableField<String> = ObservableField()
    private val repository: UserRepository
    val readAllData: LiveData<List<DemoModel>>
    var id: ObservableInt = ObservableInt(0)

    var isEdit: ObservableBoolean = ObservableBoolean(false)

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun addUser(user: DemoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }


    fun updateUser(user: DemoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: DemoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }


    fun deleteOldUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOldUser()
        }
    }

    fun onClick(view: View) {
        when (view.id) {

            R.id.ivBack -> {
                view.findNavController().popBackStack()
            }

            R.id.btnAdd -> {
                val validation = Validation.create().apply {
                    isEmpty(firstName.get(), "Please enter first name")
                    isEmpty(lastName.get(), "Please enter last name")
                    isEmpty(age.get(), "Please enter age")
                }
                if (validation.isValid()) {
                    val demoData = DemoModel(
                        id = id.get(), firstName = firstName.get(),
                        lastName = lastName.get(),
                        age = age.get()
                    )
                    Log.e("TAG", "onClick: ==== $demoData")
                    if (isEdit.get())
                        updateUser(demoData)
                    else
                        addUser(demoData)
                    view.findNavController().popBackStack()
                }

            }
        }
    }


}
