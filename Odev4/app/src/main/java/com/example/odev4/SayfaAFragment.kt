package com.example.odev4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.odev4.databinding.FragmentSayfaABinding


class SayfaAFragment : Fragment() {
    private lateinit var binding : FragmentSayfaABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaABinding.inflate(inflater,container,false)

        binding.button3.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.bgidis)
        }
        return binding.root
    }

}