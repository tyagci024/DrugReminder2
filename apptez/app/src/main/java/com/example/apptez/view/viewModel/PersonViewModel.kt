package com.example.apptez.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apptez.view.model.Drug
import com.example.apptez.view.model.Person
import com.example.apptez.view.service.DrugDatabase
import com.example.apptez.view.service.DrugRepo

import kotlinx.coroutines.launch
import java.util.UUID

class PersonViewModel(application: Application): AndroidViewModel(application) /*{

    var personData: LiveData<List<Person>>

    var repostory: PersonRepo

    init {
        val personDao = PersonDatabase.getDatabase(application).personDao()
        repostory = PersonRepo(personDao)
        personData = personDao.getAllPerson()
    }


    fun addPerson(person: Person) {
        viewModelScope.launch {
            repostory.addPerson(person)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repostory.deleteAll()
        }
    }

    fun getPerson() {
        viewModelScope.launch {
            repostory.getPerson()
        }
    }
}*/




