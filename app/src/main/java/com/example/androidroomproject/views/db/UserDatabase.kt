package com.example.androidroomproject.views.db

import android.content.Context
import androidx.room.*
import com.example.androidroomproject.model.DemoModel


@Database(entities = [DemoModel::class], version = 10, exportSchema = false)


//No change to be done here if and only if schema is changed..change the version number

abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}