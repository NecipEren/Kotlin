package com.example.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.data.model.GenelCevap
import com.example.yemeksiparisapp.data.model.Yemek
import com.example.yemeksiparisapp.data.network.ApiClient
import com.example.yemeksiparisapp.databinding.FragmentDetayBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private var yemek: Yemek? = null

    private val args: DetayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Fragment argümanı olarak yemek alınıyor.
        yemek = args.secilenYemek

            binding.tvDetayAd.text = yemek?.ad
            binding.tvDetayFiyat.text = "${yemek?.fiyat} ₺"

            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek?.resimAdi}"
            Picasso.get().load(imageUrl).into(binding.imgDetayResim)

            binding.btnSepeteEkle.setOnClickListener {
                val adet = binding.etAdet.text.toString().toIntOrNull()
                if(adet!=null && adet>0){
                    sepeteEkle(yemek!!,adet)
                } else {
                    Toast.makeText(requireContext(),"Geçerli bir değer girin", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sepeteEkle(yemek: Yemek,adet: Int){
        ApiClient.retrofit.sepeteYemekEkle(
            yemekAdi = yemek.ad,
            resimAdi = yemek.resimAdi,
            fiyat = yemek.fiyat,
            adet = adet,
            kullaniciAdi = "eren_turkoglu"
        ).enqueue(object : Callback<GenelCevap>{
            override fun onResponse(call: Call<GenelCevap>, response: Response<GenelCevap>) {
                if(response.isSuccessful){
                    Toast.makeText(requireContext(),"Sepete eklendi", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                } else {
                    Toast.makeText(requireContext(),"Hata: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GenelCevap>, t: Throwable) {
                Toast.makeText(requireContext(),"Hata: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}