package com.example.yemeksiparisapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisapp.data.model.Yemek
import com.example.yemeksiparisapp.data.model.YemeklerCevap
import com.example.yemeksiparisapp.data.network.ApiClient
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainViewModel : ViewModel() {
    private val _yemekler = MutableLiveData<List<Yemek>>()
    val yemekler: LiveData<List<Yemek>> get() = _yemekler

    private val _hataMesaji = MutableLiveData<String?>()
    val hataMesaji: LiveData<String?> get() = _hataMesaji

    fun yemekleriGetir(){
        ApiClient.retrofit.tumYemekleriGetir().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap?>, response: Response<YemeklerCevap?>) {
                if(response.isSuccessful){
                    _yemekler.value = response.body()?.yemekler ?: emptyList()
                    _hataMesaji.value = null
                } else {
                    _hataMesaji.value = "Hata: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<YemeklerCevap?>, t: Throwable) {
                _hataMesaji.value = "Bağlantı hatası: ${t.localizedMessage}"
            }
        })
    }

}