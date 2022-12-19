package com.example.androidroomproject.views.home

import android.app.AlertDialog
import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.androidroomproject.R
import com.example.androidroomproject.model.DemoModel
import com.example.androidroomproject.recycleradapter.RecyclerAdapter
import com.example.androidroomproject.repository.UserRepository
import com.example.androidroomproject.views.db.UserDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application) : ViewModel() {

    val adapter by lazy { RecyclerAdapter<DemoModel>(R.layout.home_list_item) }
    private val repository: UserRepository
    val readAllData: LiveData<List<DemoModel>>

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun deleteUser(user: DemoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAll(list: ArrayList<DemoModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll(list)
        }
    }


    var adapterClick = RecyclerAdapter.OnItemClick { view, viewHolder, type ->
        when (view.id) {
            R.id.ivDelete -> {
                deleteUser(adapter.getItemAt(viewHolder?.adapterPosition ?: 0))
            }

            R.id.llItem -> {
                adapter.getItemAt(viewHolder?.adapterPosition ?: 0).selected.set(true)
                view.findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToAddUpdateFragment(
                            data = adapter.getItemAt(
                                viewHolder?.adapterPosition ?: 0
                            )
                        )
                    )
            }
        }
    }


    fun onClick(view: View) {
        when (view.id) {

            R.id.ivAdd -> {
                view.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToAddUpdateFragment())
            }
            R.id.ivDeleteAll -> {
                showDeleteAllDialog(view)
            }
        }
    }

    init {
        adapter.setOnItemClick(adapterClick)
    }

    private fun showDeleteAllDialog(view: View) {
        val logout = AlertDialog.Builder(view.context)
        logout.setTitle("Are you sure you delete all?")
        logout.setCancelable(false)
        logout.setPositiveButton("OK") { dialogInterface, _ ->
            deleteAll(adapter.getAllItems())
            dialogInterface.cancel()
            dialogInterface.dismiss()
        }
        logout.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.cancel()
            dialogInterface.dismiss()
        }
        logout.create()
        logout.show()
    }
}

