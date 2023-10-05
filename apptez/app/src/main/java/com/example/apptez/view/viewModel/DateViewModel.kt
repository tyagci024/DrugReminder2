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
import com.example.apptez.view.model.CheckUpDate
import com.example.apptez.view.serviceDate.DateDatabase
import com.example.apptez.view.serviceDate.DateRepo
import kotlinx.coroutines.launch
import java.util.*

class DateViewModel(application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<CheckUpDate>>
    var repostory : DateRepo

    init {
        val dateDao = DateDatabase.getDatabase(application).dateDao()
        repostory = DateRepo(dateDao)
        readAllData = dateDao.getAllDate()
    }
    fun impDate(checkUpDate: CheckUpDate) {
        viewModelScope.launch {
            repostory.addDate(checkUpDate)
            setAlarm(getApplication(), checkUpDate, checkUpDate.id, checkUpDate.Doctor, checkUpDate.Hospital, checkUpDate.time)
        }
    }

    fun update(date: CheckUpDate) {
        viewModelScope.launch {
            repostory.update(date)
        }
    }

    fun delete(date: CheckUpDate) {
        viewModelScope.launch {
            repostory.deleteDate(date)
        }
    }

    fun removeDate(checkUpDate: CheckUpDate) {
        viewModelScope.launch {
            repostory.deleteDate(checkUpDate)
        }
    }
    fun removeAll(){
        viewModelScope.launch {
            repostory.deleteAll()
        }
    }
    fun setAlarm(context: Context, checkUpDate: CheckUpDate, alarmId: Int, doctor :String,hospital:String,time:Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("REMINDER_TYPE", "CHECK_UP")
        intent.putExtra("REMINDER_TITLE", hospital)
        intent.putExtra("DESC", doctor)
        intent.putExtra("ALARM_ID", alarmId)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = checkUpDate.Date
            add(Calendar.HOUR_OF_DAY, -3)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
        )

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.SCHEDULE_EXACT_ALARM)
                == PackageManager.PERMISSION_GRANTED) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}
