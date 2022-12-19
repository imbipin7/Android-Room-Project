package com.example.androidroomproject.model

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.androidroomproject.recycleradapter.AbstractModel
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user_table")
data class DemoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    var firstName: String? = null,
    var lastName: String? = null,
    var age: String? = null,


    ) : AbstractModel(), Parcelable {
    @Ignore
    var selected: ObservableBoolean = ObservableBoolean(false)

}
