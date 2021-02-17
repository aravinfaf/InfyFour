package com.app.infyfour.application

import android.app.Application
import com.app.infythree.viewmodel.factory.ViewModelFactory

class MyApp : Application() {

    private lateinit var app: MyApp
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        app=this
        viewModelFactory = ViewModelFactory(applicationContext)
    }
}