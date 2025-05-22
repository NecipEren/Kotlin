package com.example.odev7.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.odev7.data.entity.ToDo
import com.example.odev7.databinding.FragmentKayitBinding
import com.example.odev7.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KayitFragment : Fragment() {

    private lateinit var binding: FragmentKayitBinding
    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKayitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonKaydet.setOnClickListener {
            val name = binding.editTextToDo.text.toString().trim()

            if (name.isNotEmpty()) {
                val newToDo = ToDo(name = name) // id default 0
                viewModel.addToDo(newToDo.name)

                Toast.makeText(requireContext(), "Eklendi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Lütfen bir metin girin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
