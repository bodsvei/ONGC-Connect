package com.example.ongc.ui.serverstats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ongc.databinding.FragmentServerStatsBinding

class ServerStatsFragment : Fragment() {

    private var _binding: FragmentServerStatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var serverStatsViewModel: ServerStatsViewModel
    private lateinit var serverStatsAdapter: ServerStatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        serverStatsViewModel = ViewModelProvider(this).get(ServerStatsViewModel::class.java)

        _binding = FragmentServerStatsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupRefreshButton()
        observeViewModel()

        return root
    }

    private fun setupRecyclerView() {
        serverStatsAdapter = ServerStatsAdapter(emptyList()) { serverStat ->
            // Handle server stat click
            Toast.makeText(context, "Clicked: ${serverStat.instanceName}", Toast.LENGTH_SHORT).show()
        }

        binding.serverStatsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = serverStatsAdapter
        }
    }

    private fun setupRefreshButton() {
        binding.refreshButton.setOnClickListener {
            serverStatsViewModel.refreshServerStats()
            Toast.makeText(context, "Refreshing server stats...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        serverStatsViewModel.serverStats.observe(viewLifecycleOwner) { stats ->
            serverStatsAdapter.updateServerStats(stats)
            
            // Show/hide empty state
            if (stats.isEmpty()) {
                binding.emptyStateText.visibility = View.VISIBLE
                binding.serverStatsRecyclerView.visibility = View.GONE
            } else {
                binding.emptyStateText.visibility = View.GONE
                binding.serverStatsRecyclerView.visibility = View.VISIBLE
            }
        }

        serverStatsViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 