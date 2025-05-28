package com.example.yemeksiparisapp.data.network

import com.example.yemeksiparisapp.data.model.GenelCevap
import com.example.yemeksiparisapp.data.model.SepetCevap
import retrofit2.Call
import com.example.yemeksiparisapp.data.model.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerApiService {
    @GET("tumYemekleriGetir.php")
    fun tumYemekleriGetir() : Call<YemeklerCevap>

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteYemekEkle(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") resimAdi: String,
        @Field("yemek_fiyat") fiyat: Int,
        @Field("yemek_siparis_adet") adet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Call<GenelCevap>

    @FormUrlEncoded
    @POST("sepettekiYemekleriGetir.php")
    fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Call<SepetCevap>

    @FormUrlEncoded
    @POST("sepettenYemekSil.php")
    fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Call<GenelCevap>
}