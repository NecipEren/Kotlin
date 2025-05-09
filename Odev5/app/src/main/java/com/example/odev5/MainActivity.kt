package com.example.odev5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var girilenSayi = ""
    private var ilkSayi: Int? = null
    private var toplamaModu = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sayiButonlari = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3, binding.button4,
            binding.button5, binding.button6, binding.button7, binding.button8, binding.button9
        )

        // Rakam butonları
        for (buton in sayiButonlari) {
            buton.setOnClickListener {
                girilenSayi += buton.text
                binding.textViewSonuc.text = girilenSayi
            }
        }

        // + butonu
        binding.buttonTopla.setOnClickListener {
            if (girilenSayi.isNotEmpty()) {
                ilkSayi = girilenSayi.toInt()
                girilenSayi = ""
                toplamaModu = true
                binding.textViewSonuc.text = "+"
            }
        }

        // = butonu
        binding.buttonEsittir.setOnClickListener {
            if (toplamaModu && girilenSayi.isNotEmpty()) {
                val ikinciSayi = girilenSayi.toInt()
                val sonuc = (ilkSayi ?: 0) + ikinciSayi
                binding.textViewSonuc.text = sonuc.toString()

                // Reset
                girilenSayi = ""
                ilkSayi = null
                toplamaModu = false
            }
        }

        // C (temizleme) butonu (isteğe bağlı)
        binding.buttonReset.setOnClickListener {
            girilenSayi = ""
            ilkSayi = null
            toplamaModu = false
            binding.textViewSonuc.text = "0"
        }
    }
}