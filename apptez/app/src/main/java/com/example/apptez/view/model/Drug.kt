package com.example.apptez.view.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "drug_table")
data class Drug(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var description:String,
    val time: Long,
    val repeat: Boolean,
    val endDate: Long,
    val timesPerDay:Int

) : Parcelable
