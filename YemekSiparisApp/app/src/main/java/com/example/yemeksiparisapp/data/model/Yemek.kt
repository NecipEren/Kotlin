package com.example.yemeksiparisapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Yemek(
    @SerializedName("yemek_id") val id: Int,
    @SerializedName("yemek_adi") val ad: String,
    @SerializedName("yemek_resim_adi") val resimAdi: String,
    @SerializedName("yemek_fiyat") val fiyat: Int
) : Serializable {
}