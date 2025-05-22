package com.example.odev7.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.odev7.databinding.FragmentDetayBinding
import com.example.odev7.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: ToDoViewModel by viewModels()

    // Navigation Component ile gelen argüman
    val args: DetayFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val selectedToDo = args.toDo

        super.onViewCreated(view, savedInstanceState)

        // EditText'e mevcut veriyi yaz
        binding.editTextToDoDetay.setText(selectedToDo.name)

        // Güncelle Butonu
        binding.buttonGuncelle.setOnClickListener {
            val updatedText = binding.editTextToDoDetay.text.toString().trim()
            if (updatedText.isNotEmpty()) {
                val updatedToDo = selectedToDo.copy(name = updatedText)
                viewModel.updateToDo(updatedToDo)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Boş bırakmayın!", Toast.LENGTH_SHORT).show()
            }
        }

        // Sil Butonu
        binding.buttonSil.setOnClickListener {
            viewModel.deleteToDo(selectedToDo)
            findNavController().popBackStack()
        }
    }
}
