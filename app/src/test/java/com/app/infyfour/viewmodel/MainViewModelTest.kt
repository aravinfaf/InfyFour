package com.app.infyfour.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.infyfour.RxImmediateSchedulerRule
import com.app.infyfour.data.model.CountryMainModel
import com.app.infyfour.data.model.CountryModel
import com.app.infyfour.repository.CountryRepo
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{

    @Rule
    val instantaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    val repository = CountryRepo()

    @Mock
    lateinit var mainViewModel: MainViewModel

    @Mock
    val apidetails : ArrayList<CountryModel> = arrayListOf(
        CountryModel("Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment.",
            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg")
    )

    @Mock
    val apiresponse = CountryMainModel("",apidetails)

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(CountryRepo())
    }

    @Test
    fun testApiSuccess(){
        `when`(repository.getApiDetails()).thenReturn(Observable.just(apiresponse))
        mainViewModel.fetchUsers()
        assertEquals(apiresponse.rows,mainViewModel.livedata.value)
    }
}