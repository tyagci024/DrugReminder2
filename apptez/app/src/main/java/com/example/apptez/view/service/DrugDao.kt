package com.example.apptez.view.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.apptez.view.model.Drug

@Dao
interface DrugDao {

    @Insert
    suspend fun addDrug(drug: Drug)

    @Delete
    suspend fun deleteDrug(drug:Drug)

    @Update
    suspend fun updateDrug(drug: Drug)


    @Query("SELECT * from drug_table order by id ASC")
    fun getAllDrugs(): LiveData<List<Drug>>

    @Query("SELECT * FROM drug_table WHERE id = :id")
    fun getReminderById(id: Int): Drug

    @Query("DELETE FROM drug_table WHERE id = :drugId")
    suspend fun deleteDrugById(drugId: Int)

    @Query("DELETE FROM drug_table")
    suspend fun deleteAll()









}