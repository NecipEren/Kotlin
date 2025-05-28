package com.example.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparisapp.R
import com.example.yemeksiparisapp.databinding.FragmentYemekListBinding
import com.example.yemeksiparisapp.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisapp.viewmodel.MainViewModel


class YemekListFragment : Fragment() {

    private lateinit var binding: FragmentYemekListBinding
    private val viewModel : MainViewModel by viewModels()
    private lateinit var adapter: YemeklerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYemekListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvYemekler.layoutManager = LinearLayoutManager(requireContext())

        binding.btnSepet.setOnClickListener {
            val action = YemekListFragmentDirections.sepetGecis()
            findNavController().navigate(action)
        }


        adapter = YemeklerAdapter(emptyList()){ secilenYemek ->
            val action = YemekListFragmentDirections
                .detayGecis(secilenYemek)
            findNavController().navigate(action)
        }

        binding.rvYemekler.adapter = adapter

        viewModel.yemekler.observe(viewLifecycleOwner) { liste->
            adapter.updateData(liste)
        }


        viewModel.yemekleriGetir()
    }
}