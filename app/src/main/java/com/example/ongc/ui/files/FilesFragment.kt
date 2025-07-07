package com.example.ongc.ui.files

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ongc.R
import com.example.ongc.databinding.FragmentFilesBinding

class FilesFragment : Fragment() {

    private var _binding: FragmentFilesBinding? = null
    private val binding get() = _binding!!
    private lateinit var filesViewModel: FilesViewModel
    private lateinit var filesAdapter: FilesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        filesViewModel = ViewModelProvider(this).get(FilesViewModel::class.java)

        _binding = FragmentFilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupSearchBar()
        observeViewModel()

        return root
    }

    private fun setupRecyclerView() {
        filesAdapter = FilesAdapter(emptyList()) { file ->
            // Handle file click
            Toast.makeText(context, "Clicked: ${file.name}", Toast.LENGTH_SHORT).show()
        }

        binding.filesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filesAdapter
        }
    }

    private fun setupSearchBar() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filesViewModel.setSearchQuery(s?.toString() ?: "")
            }
        })
        
        // Check for QR scanner search query
        checkForQRSearchQuery()
    }
    
    private fun checkForQRSearchQuery() {
        val sharedPrefs = requireContext().getSharedPreferences("QRSearch", Context.MODE_PRIVATE)
        val searchQuery = sharedPrefs.getString("search_query", null)
        
        if (searchQuery != null) {
            // Set the search query in the search bar
            binding.searchEditText.setText(searchQuery)
            filesViewModel.setSearchQuery(searchQuery)
            
            // Clear the stored search query
            sharedPrefs.edit().remove("search_query").apply()
            
            // Show a toast message
            Toast.makeText(context, getString(R.string.qr_scanner_searching_for, searchQuery), Toast.LENGTH_LONG).show()
        }
    }

    private fun observeViewModel() {
        filesViewModel.filteredFiles.observe(viewLifecycleOwner) { files ->
            filesAdapter.updateFiles(files)
            
            // Show/hide empty state
            if (files.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.filesRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                binding.filesRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 