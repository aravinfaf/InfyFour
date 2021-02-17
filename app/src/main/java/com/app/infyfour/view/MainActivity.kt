package com.app.infyfour.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.infyfour.R
import com.app.infyfour.adapter.CountryAdapter
import com.app.infyfour.application.MyApp
import com.app.infyfour.data.model.CountryModel
import com.app.infyfour.databinding.ActivityMainBinding
import com.app.infyfour.utils.Status
import com.app.infyfour.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var countryAdapter : CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpViewmodel()
        setUpObserver()
    }

    fun setUpObserver(){
        mainViewModel.livedata.observe(this, Observer {
            when(it.status){
                Status.LOADING->{
                    activityMainBinding.progressBar.visibility = View.VISIBLE
                    activityMainBinding.countryRecyclerview.visibility = View.GONE
                }

                Status.SUCCESS->{
                    activityMainBinding.progressBar.visibility = View.GONE
                    activityMainBinding.countryRecyclerview.visibility = View.VISIBLE
                    it.data?.let { it1 -> renderData(it1) }
                }

                Status.ERROR->{
                    activityMainBinding.progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderData(data: List<CountryModel>) {

        activityMainBinding.countryRecyclerview.layoutManager = LinearLayoutManager(this)
        countryAdapter =
            CountryAdapter(data as ArrayList<CountryModel>)
        activityMainBinding.countryRecyclerview.addItemDecoration(
            DividerItemDecoration(
                activityMainBinding.countryRecyclerview.context,
                (activityMainBinding.countryRecyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        activityMainBinding.countryRecyclerview.adapter = countryAdapter
    }

    fun setUpViewmodel(){
        val factory = (this.applicationContext as MyApp).viewModelFactory
        mainViewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)
    }
}