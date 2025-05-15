package com.example.odev6

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odev6.databinding.HizlimarketcardTasarimBinding

class YemekAdapter(var mContext: Context,var altListe: List<Yemek>)
    : RecyclerView.Adapter<YemekAdapter.cardTasarimTutucu>() {

    inner class cardTasarimTutucu(var tasarim : HizlimarketcardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardTasarimTutucu {
        var binding = HizlimarketcardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return cardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: cardTasarimTutucu, position: Int) {
        val urun = altListe.get(position)
        val t = holder.tasarim
        t.textViewBaslik.text = "${urun.isim}"
        t.imageViewicon.setImageResource(
            mContext.resources.getIdentifier(urun.resim,"drawable",mContext.packageName)
        )

    }

    override fun getItemCount(): Int {
        return altListe.size
    }

}