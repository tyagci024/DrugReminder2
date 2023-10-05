package com.example.apptez.view.serviceDate

import androidx.lifecycle.LiveData
import com.example.apptez.view.model.CheckUpDate
import com.example.apptez.view.model.Drug

class DateRepo( val dateDao: DateDao) {
    val readAllData: LiveData<List<CheckUpDate>> = dateDao.getAllDate()

    suspend fun addDate(checkUpDate: CheckUpDate){
        dateDao.addDate(checkUpDate)
    }

    suspend fun deleteDate(checkUpDate: CheckUpDate){
        dateDao.deleteDate(checkUpDate)
    }
    suspend fun deleteAll(){
        dateDao.deleteAll()
    }

    suspend fun update(date:CheckUpDate){
        dateDao.update(date)
    }
}