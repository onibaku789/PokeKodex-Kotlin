package com.example.android.pokekodex.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.pokekodex.network.Pokemon

class SearchViewModel: ViewModel(){

    private val __navigateToDetails= MutableLiveData<Pokemon>()

    val navigateToDetails: LiveData<Pokemon>
        get() = __navigateToDetails
    fun doneNavigating() {
        __navigateToDetails.value = null
        }


   /* override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
*/
    }