package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList

    //the following method not working with me

//    val shoesList: LiveData<MutableList<Shoe>>
//        get() = _shoesList!!

    fun addShoe(shoe: Shoe) {
        _shoesList.value = listOf(shoe)
    }

    init {
        Timber.tag(TAG).i(": MainViewModel created")

//
//        _shoesList.value?.add(testShoe1)
//        _shoesList.value?.add(testShoe2)
    }


}