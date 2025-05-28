package com.example.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.model.GenelCevap
import com.example.yemeksiparisapp.data.model.SepetCevap
import com.example.yemeksiparisapp.data.model.SepetYemek
import com.example.yemeksiparisapp.data.network.ApiClient
import com.example.yemeksiparisapp.databinding.FragmentSepetBinding
import com.example.yemeksiparisapp.ui.adapter.SepetAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var adapter: SepetAdapter
    private val kullaniciAdi = "eren_turkoglu"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SepetAdapter(emptyList()){ silinecekYemek ->
            sepettenYemekSil(silinecekYemek)
        }

        binding.rvSepet.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSepet.adapter = adapter

        sepetiYukle()
    }

    private fun sepetiYukle() {
        ApiClient.retrofit.sepettekiYemekleriGetir(kullaniciAdi)
            .enqueue(object : Callback<SepetCevap>{
                override fun onResponse(call: Call<SepetCevap?>, response: Response<SepetCevap?>) {
                    if(response.isSuccessful){
                        val liste = response.body()?.sepetYemekler ?: emptyList()
                        adapter.updateData(liste)

                        if(liste.isEmpty()){
                            binding.tvSepetBos.visibility = View.VISIBLE
                            binding.tvToplamFiyat.visibility = View.GONE
                            binding.tvToplamFiyat.text = ""
                        } else{
                            binding.tvSepetBos.visibility = View.GONE
                            binding.tvToplamFiyat.visibility = View.VISIBLE
                            toplamFiyatHesapla(liste)
                        }

                    } else{
                        Toast.makeText(requireContext(),"Hata: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SepetCevap?>, t: Throwable) {
                    Toast.makeText(requireContext(),"Bağlantı hatası: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun sepettenYemekSil(yemek: SepetYemek) {
        ApiClient.retrofit.sepettenYemekSil(yemek.sepetYemekId.toInt(),kullaniciAdi)
            .enqueue(object : Callback<GenelCevap>{
                override fun onResponse(call: Call<GenelCevap>, response: Response<GenelCevap>){
                    if(response.isSuccessful){
                        Toast.makeText(requireContext(),"Silindi", Toast.LENGTH_SHORT).show()
                        adapter.removeItem(yemek)

                        val kalanListe = adapter.getCurrentList()
                        if (kalanListe.isEmpty()) {
                            binding.tvSepetBos.visibility = View.VISIBLE
                            binding.tvToplamFiyat.visibility = View.GONE
                            binding.tvToplamFiyat.text = ""
                        } else {
                            binding.tvSepetBos.visibility = View.GONE
                            binding.tvToplamFiyat.visibility = View.VISIBLE
                            toplamFiyatHesapla(kalanListe)
                        }

                    } else {
                        Toast.makeText(requireContext(), "Silinemedi", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<GenelCevap>, t: Throwable) {
                    Toast.makeText(requireContext(),"Hata: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun toplamFiyatHesapla(liste: List<SepetYemek>) {
        val toplam = liste.sumOf {
            (it.yemekFiyat.toIntOrNull() ?: 0) * (it.siparisAdet.toIntOrNull() ?: 0)
        }
        binding.tvToplamFiyat.text = "Toplam: $toplam ₺"
    }


}