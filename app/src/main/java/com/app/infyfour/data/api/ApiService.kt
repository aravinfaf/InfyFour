package com.app.infyfour.data.api

import com.app.infyfour.data.model.CountryMainModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("facts.json")
    fun getApi():Observable<CountryMainModel>
}