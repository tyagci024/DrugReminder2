package com.example.apptez.view.viewModel

import android.Manifest
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.apptez.view.AlarmReceiver
import com.example.apptez.view.model.Drug
import com.example.apptez.view.service.DrugDatabase
import com.example.apptez.view.service.DrugRepo
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class DrugViewModel(application: Application): AndroidViewModel(application) {

    var readAllData: LiveData<List<Drug>>
    var repostory : DrugRepo

    init {
        val drugDao=DrugDatabase.getDatabase(application).drugDao()
        repostory= DrugRepo(drugDao)
        readAllData=drugDao.getAllDrugs()
    }
    fun impDrug(drug: Drug){
        viewModelScope.launch {
            repostory.addDrug(drug)
            setAlarm(drug.id, drug.time, drug.repeat, drug.endDate, drug.name, drug.timesPerDay,drug.description)

        }

    }
    fun deleteAll(){
        viewModelScope.launch {
            repostory.deleteAll()
        }
    }

    fun removeDrug(drug: Drug){
        viewModelScope.launch {
            repostory.deleteDrug(drug)
        }

    }

    fun update(drug: Drug){
        viewModelScope.launch {
            repostory.updateDrug(drug)
            setAlarm(drug.id, drug.time, drug.repeat, drug.endDate, drug.name, drug.timesPerDay,drug.description)

        }

    }

    fun delete(drug: Drug){
        viewModelScope.launch {
            repostory.deleteDrug(drug)
        }

    }

    fun setAlarm(alarmId: Int, time: Long, repeat: Boolean, endDate: Long, reminderTitle: String, timesPerDay: Int, reminderDesc: String) {
        val alarmManager =
            getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(getApplication(), AlarmReceiver::class.java).apply {
            val drug = "DRUG"
            putExtra("ALARM_ID", alarmId)
            putExtra("REPEAT", repeat)
            putExtra("END_DATE", endDate)
            putExtra("REMINDER_TITLE", reminderTitle)
            putExtra("TIMES_PER_DAY", timesPerDay)
            putExtra("DESC", reminderDesc)
            putExtra("REMINDER_TYPE", drug)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            alarmId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
        )

        val interval: Long = if (timesPerDay == 2) {
            TimeUnit.HOURS.toMillis(12) // 12 saatte bir
        } else {
            TimeUnit.HOURS.toMillis(5) // 5 saatte bir
        }

        if (repeat) {
            val repeatInterval = AlarmManager.INTERVAL_DAY // 24 saat
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (ContextCompat.checkSelfPermission(
                        getApplication(),
                        Manifest.permission.SCHEDULE_EXACT_ALARM
                    )
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
                    alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        time,
                        repeatInterval,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    time,
                    repeatInterval,
                    pendingIntent
                )
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (ContextCompat.checkSelfPermission(
                        getApplication(),
                        Manifest.permission.SCHEDULE_EXACT_ALARM
                    )
                    == PackageManager.PERMISSION_GRANTED
                ) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
                }
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
            }
        }
    }}