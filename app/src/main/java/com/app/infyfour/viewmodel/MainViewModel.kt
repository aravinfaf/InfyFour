package com.app.infyfour.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.infyfour.data.model.CountryMainModel
import com.app.infyfour.data.model.CountryModel
import com.app.infyfour.repository.CountryRepo
import com.app.infyfour.utils.Resource
import com.app.infyfour.utils.Resource.Companion.loading
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: CountryRepo) : ViewModel() {

    var countrylist  = arrayListOf<CountryModel>()
    var livedata = MutableLiveData<Resource<List<CountryModel>>>()
    lateinit var disposabledata : Disposable
    init {
        fetchUsers()
    }

    fun fetchUsers() {

        livedata.value=(loading(null))

       disposabledata= repository.getApiDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response -> onResponse(response)},
                {error -> onError(error)
            })
    }

    private fun onError(error: Throwable) {
        livedata.value=(error(error.message.toString()))
    }

    private fun onResponse(response: CountryMainModel) {
        countrylist = response.rows as ArrayList<CountryModel>
        livedata.value=(Resource.success(this.countrylist))
    }

    override fun onCleared() {
        super.onCleared()
        disposabledata.dispose()
    }
}


