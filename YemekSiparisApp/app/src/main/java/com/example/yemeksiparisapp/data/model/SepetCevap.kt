package com.example.yemeksiparisapp.data.model

import com.google.gson.annotations.SerializedName

data class SepetCevap(
    @SerializedName("sepet_yemekler") val sepetYemekler: List<SepetYemek>,
    @SerializedName("success") val success: Int
)
