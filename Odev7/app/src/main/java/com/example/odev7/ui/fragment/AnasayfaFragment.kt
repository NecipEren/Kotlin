package com.example.odev7.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odev7.databinding.FragmentAnasayfaBinding
import com.example.odev7.ui.adapter.ToDoAdapter
import com.example.odev7.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {

    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ToDoAdapter
    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root

        viewModel.loadToDos()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // RecyclerView setup
        adapter = ToDoAdapter { selectedToDo ->
            val action = AnasayfaFragmentDirections.detaygecis(selectedToDo)
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // ToDo listesi akışını gözlemle
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.toDoList.collectLatest { list ->
                adapter.submitList(list)
            }
        }

        viewModel.loadToDos()

        // Kaydet sayfasına geçiş
        binding.fab.setOnClickListener {
            findNavController().navigate(AnasayfaFragmentDirections.kayitgecis())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
