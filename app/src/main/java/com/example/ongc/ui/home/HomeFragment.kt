package com.example.ongc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ongc.R
import com.example.ongc.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupWidgetClickListeners()

        return root
    }

    private fun setupWidgetClickListeners() {
        // Files Widget
        binding.filesWidget.setOnClickListener {
            findNavController().navigate(R.id.nav_files)
        }

        // QR Scanner Widget
        binding.qrScannerWidget.setOnClickListener {
            findNavController().navigate(R.id.nav_qr_scanner)
        }

        // Server Stats Widget
        binding.serverStatsWidget.setOnClickListener {
            findNavController().navigate(R.id.nav_server_stats)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}