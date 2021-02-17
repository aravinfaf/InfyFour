package com.app.infyfour.repository

import com.app.infyfour.data.api.ApiService
import com.app.infyfour.data.api.RetrofitBuilder

class CountryRepo {
    private var apiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)
    fun getApiDetails() = apiService.getApi()
}