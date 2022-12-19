package com.example.androidroomproject.repository

import androidx.lifecycle.LiveData
import com.example.androidroomproject.model.DemoModel
import com.example.androidroomproject.views.db.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<DemoModel>> = userDao.readAllData()

    suspend fun addUser(user: DemoModel) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: DemoModel) {
        userDao.updateUser(user)
    }


    suspend fun deleteUser(user: DemoModel) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAll(list: ArrayList<DemoModel>) {
        userDao.deleteAll(list)
    }


    suspend fun deleteOldUser() {
        userDao.deleteOldUser()
    }

}