package com.example.odev6

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odev6.databinding.HizlimarketcardTasarimBinding

class MarketAdapter(var mContext: Context,var ustListe: List<Market>)
    : RecyclerView.Adapter<MarketAdapter.cardTasarimTutucu>() {

    inner class cardTasarimTutucu(var tasarim : HizlimarketcardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardTasarimTutucu {
        var binding = HizlimarketcardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return cardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: cardTasarimTutucu, position: Int) {
        val urun = ustListe.get(position)
        val t = holder.tasarim
        t.textViewBaslik.text = "${urun.isim}"
        t.imageViewicon.setImageResource(
            mContext.resources.getIdentifier(urun.resim,"drawable",mContext.packageName)
        )
        t.textViewBaslik.setTextColor(urun.textColor)
        t.fab.backgroundTintList = ColorStateList.valueOf(urun.textColor)
    }

    override fun getItemCount(): Int {
        return ustListe.size
    }

}