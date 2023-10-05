package com.example.apptez.view.servicepharmacy

import com.example.apptez.view.model.Pharmacy
import io.reactivex.Single
import retrofit2.http.GET

interface PharmacyApi {

    @GET("tyagci024/pharmacyapi/main/pharmacy.json")
    fun getCountries(): Single<List<Pharmacy>>
}