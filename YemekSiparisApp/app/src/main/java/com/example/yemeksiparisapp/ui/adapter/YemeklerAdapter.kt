package com.example.yemeksiparisapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.yemeksiparisapp.data.model.Yemek
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.ItemYemekBinding
import com.squareup.picasso.Picasso

class YemeklerAdapter(
    private var yemekListesi: List<Yemek>,
    private val onItemClick : (Yemek) -> Unit
) : RecyclerView.Adapter<YemeklerAdapter.YemekViewHolder>(){

    fun updateData(yeniListe : List<Yemek>) {
        yemekListesi = yeniListe
        notifyDataSetChanged()
    }

    inner class YemekViewHolder(private val binding: ItemYemekBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(yemek: Yemek){
            binding.tvYemekAdi.text =yemek.ad
            binding.tvYemekFiyat.text = "${yemek.fiyat} ₺"

            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.resimAdi}"
            Picasso.get().load(imageUrl).into(binding.imgYemek)

            binding.root.setOnClickListener {
                onItemClick(yemek)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding = ItemYemekBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(yemekListesi[position])
    }

    override fun getItemCount(): Int = yemekListesi.size

}