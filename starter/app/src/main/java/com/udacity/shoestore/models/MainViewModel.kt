package com.udacity.shoestore.models

import android.accounts.AuthenticatorDescription
import android.icu.text.AlphabeticIndex
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {


    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoesList

    private val _eventCloseScreen = MutableLiveData<Boolean?>()
    val eventCloseScreen: LiveData<Boolean?>
        get() = _eventCloseScreen

    lateinit var currentShoe:Shoe


    //the following method not working with me

//    val shoesList: LiveData<MutableList<Shoe>>
//        get() = _shoesList!!

    fun addShoe(shoeName: String,  shoeSize: Double, shoeCompany: String, shoeDescription: String) {

         currentShoe = Shoe(
            name = shoeName,
            size = shoeSize,
            company = shoeCompany,
            description = shoeDescription
        )
        _shoesList.value = _shoesList.value!! + currentShoe
        _eventCloseScreen.value = true
    }

    init {

        _shoesList.value = createSampleShoes()

//        addShoe(testShoe1)


//
//        _shoesList.value?.add(testShoe1)
//        _shoesList.value?.add(testShoe2)
    }


    private fun createSampleShoes(): List<Shoe> {
        val tempShoes = mutableListOf<Shoe>()

        tempShoes.add(Shoe("Dummy Shoe 1", 31.0, "Dummy Company", "This is a dummmy desc"))
        tempShoes.add(Shoe("Dummy Shoe 2", 40.0, "Dummy Company", "This is a dummmy desc"))

        return tempShoes
    }


    fun onEventCloseComplete() {
        _eventCloseScreen.value = null
    }
//
//    fun createNewShoe() {
//        currentShoe = Shoe("", 0.0, "", "")
//    }

    fun close() {
        _eventCloseScreen.value = true
    }


}