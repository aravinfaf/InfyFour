package com.app.infyfour.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.infyfour.data.model.CountryMainModel
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryRepoTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    val repository :  CountryRepo = CountryRepo()

    @Test
    fun getData(){
        `when`(repository.getApiDetails()).thenReturn(Observable.just(CountryMainModel()))
        verify(repository.getApiDetails())
    }
}