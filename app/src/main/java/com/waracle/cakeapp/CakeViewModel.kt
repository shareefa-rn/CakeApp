package com.waracle.cakeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CakeViewModel : ViewModel() {
    val cakesLiveData: MutableLiveData<List<Cake>> = MutableLiveData()
    private val repository = CakeRepository()

    fun fetchCakes() {
        repository.getCakes().enqueue(object : Callback<List<Cake>> {
            override fun onResponse(call: Call<List<Cake>>, response: Response<List<Cake>>) {
                if (response.isSuccessful) {
                    cakesLiveData.value = response.body()
                }
          /*      isLoading.value = false
                if (response.isSuccessful) {
                    response.body()?.let { cakes ->
                        val uniqueCakes = cakes.distinctBy { it.title } // Remove duplicates
                        val sortedCakes = uniqueCakes.sortedBy { it.title } // Sort by name
                        cakesLiveData.value = sortedCakes
                    }
                } else {
                    errorMessage.value = "Failed to fetch data"
                }*/
            }

            override fun onFailure(call: Call<List<Cake>>, t: Throwable) {
                // Handle failure
            }
        })
    }
    fun observeCakeLiveData() : LiveData<List<Cake>> {
        return cakesLiveData
    }
}
