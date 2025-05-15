package com.example.odev6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.odev6.databinding.FragmentAnasayfaBinding


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater,container,false)

        val ustListe = ArrayList<Market>()
        val f1 = Market(1,"Hemen Gelsin","sepet", ContextCompat.getColor(requireContext(),R.color.yesil))
        val f2 = Market(2,"Dilediğin saatte gelsin!","saat", ContextCompat.getColor(requireContext(),R.color.turkuaz))
        val f3 = Market(3,"Semtin Esnaflarındaki İndirimleri Keşfet","dukkan", ContextCompat.getColor(requireContext(),R.color.kirmizi))

        ustListe.add(f1)
        ustListe.add(f2)
        ustListe.add(f3)


        val altListe = ArrayList<Yemek>()
        val y1 = Yemek(1,"Yeni Go Plus","indirim")
        val y2 = Yemek(2,"Yemek Kartınla Online Öde!","online")
        val y3 = Yemek(3,"Kampanyalı restoranlar","pizza")
        val y4 = Yemek(4,"Yakınımdaki restoranlar","restoran")
        val y5 = Yemek(5,"Popüler restoranlar","yildiz")

        altListe.add(y1)
        altListe.add(y2)
        altListe.add(y3)
        altListe.add(y4)
        altListe.add(y5)

        val marketAdapter = MarketAdapter(requireContext(),ustListe)
        binding.HizliMarketRv.adapter = marketAdapter

        binding.HizliMarketRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        val yemekAdapter = YemekAdapter(requireContext(),altListe)
        binding.YemekRv.adapter = yemekAdapter

        binding.YemekRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)


        return binding.root
    }
}