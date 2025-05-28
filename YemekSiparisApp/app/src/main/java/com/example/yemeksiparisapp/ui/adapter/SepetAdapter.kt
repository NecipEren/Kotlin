package com.example.yemeksiparisapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.yemeksiparisapp.data.model.SepetYemek
import com.example.yemeksiparisapp.databinding.ItemSepetBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.processNextEventInCurrentThread

class SepetAdapter(
    private var sepetListesi: List<SepetYemek>,
    private val onSilClick: (SepetYemek) -> Unit
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>(){

    fun updateData(yeniListe: List<SepetYemek>){
        sepetListesi = yeniListe
        notifyDataSetChanged()
    }

    fun removeItem(item: SepetYemek) {
        sepetListesi = sepetListesi.filter { it.sepetYemekId != item.sepetYemekId }
        notifyDataSetChanged()
    }

    fun getCurrentList(): List<SepetYemek> = sepetListesi


    inner class SepetViewHolder(val binding: ItemSepetBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(sepetYemek: SepetYemek){
            with(binding) {
                tvYemekAdi.text = sepetYemek.yemekAdi
                tvAdet.text = "${sepetYemek.siparisAdet} adet"
                tvFiyat.text = "${sepetYemek.yemekFiyat.toInt() * sepetYemek.siparisAdet.toInt()} ₺"

                val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemekResimAdi}"
                Picasso.get().load(imageUrl).into(imgYemek)

                btnSil.setOnClickListener {
                    onSilClick(sepetYemek)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = ItemSepetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        holder.bind(sepetListesi[position])
    }

    override fun getItemCount(): Int = sepetListesi.size
}