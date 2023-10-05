package com.example.apptez.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.apptez.view.model.Pharmacy
import com.example.apptez.view.servicepharmacy.PharmacyApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PharmacyViewModel(application:Application):AndroidViewModel(application) {

    private val pharmacyApiService= PharmacyApiService()
    private val disposable= CompositeDisposable()

    val pharmacyValue=MutableLiveData<List<Pharmacy>>()



    fun getDataFromApi(){
        disposable.add(
            pharmacyApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Pharmacy>>(){
                    override fun onSuccess(t: List<Pharmacy>) {
                        pharmacyValue.value=t

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })

        )
    }

}