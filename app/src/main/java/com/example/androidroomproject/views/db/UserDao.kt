package com.example.androidroomproject.views.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidroomproject.model.DemoModel


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: DemoModel)

    @Insert
    suspend fun addUserList(user: ArrayList<DemoModel>)

    @Update
    suspend fun updateUser(user: DemoModel)

    @Delete
    suspend fun deleteUser(user: DemoModel)

    @Delete
    suspend fun deleteAll(list: ArrayList<DemoModel>)


    //Please Change the Limit to desired number if it is increased to
    // 90 then only last 90 inserted data will be present in database

    @Query("DELETE FROM user_table where id NOT IN (SELECT id from user_table ORDER BY id DESC LIMIT 2)")
    suspend fun deleteOldUser()


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<DemoModel>>


}