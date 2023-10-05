package com.example.apptez.view.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var adres:String,
    var age : Int,
    var pNumber:Int,
    var blood: String
) : Parcelable
