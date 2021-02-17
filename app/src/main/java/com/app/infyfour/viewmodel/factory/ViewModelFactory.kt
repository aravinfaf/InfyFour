package com.app.infythree.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.infyfour.repository.CountryRepo
import com.app.infyfour.viewmodel.MainViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    lateinit var context : Context
    constructor(context: Context) {
        this.context = context
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(CountryRepo()) as T
        return create(modelClass)
    }
}