package com.example.apptez.view.serviceDate

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.apptez.view.model.CheckUpDate

@Dao
interface DateDao {

    @Insert
    suspend fun addDate(checkUpDate: CheckUpDate)

    @Delete
    suspend fun deleteDate(checkUpDate: CheckUpDate)


    @Query("SELECT * from date_table order by id ASC")
    fun getAllDate(): LiveData<List<CheckUpDate>>

    @Update
    suspend fun update(checkUpDate: CheckUpDate)

    @Query("DELETE FROM date_table")
    suspend fun deleteAll()

}