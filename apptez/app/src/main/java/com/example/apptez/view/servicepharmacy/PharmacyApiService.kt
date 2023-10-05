package com.example.apptez.view.servicepharmacy

import com.example.apptez.view.model.Pharmacy
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PharmacyApiService {
    private val BASE_URL="https://raw.githubusercontent.com/"


    val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(PharmacyApi::class.java)

    fun getData(): Single<List<Pharmacy>>{
        return api.getCountries()
    }
}