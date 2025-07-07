package com.example.ongc.ui.serverstats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ServerStatsViewModel : ViewModel() {

    private val _serverStats = MutableLiveData<List<ServerStat>>()
    val serverStats: LiveData<List<ServerStat>> = _serverStats

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        loadServerStats()
    }

    fun refreshServerStats() {
        loadServerStats()
    }

    private fun loadServerStats() {
        _isLoading.value = true
        
        viewModelScope.launch {
            // Simulate API call delay
            delay(2000)
            
            val sampleStats = listOf(
                ServerStat(
                    instanceName = "web-server-01",
                    instanceId = "i-1234567890abcdef0",
                    instanceType = "t3.medium",
                    region = "us-east-1",
                    status = "running",
                    cpuUtilization = 45.2,
                    memoryUtilization = 67.8,
                    networkIn = 1024.5,
                    networkOut = 512.3,
                    diskUsage = 78.9
                ),
                ServerStat(
                    instanceName = "db-server-01",
                    instanceId = "i-0987654321fedcba0",
                    instanceType = "r5.large",
                    region = "us-west-2",
                    status = "running",
                    cpuUtilization = 23.1,
                    memoryUtilization = 89.5,
                    networkIn = 2048.7,
                    networkOut = 1024.1,
                    diskUsage = 92.3
                ),
                ServerStat(
                    instanceName = "app-server-01",
                    instanceId = "i-abcdef1234567890",
                    instanceType = "c5.xlarge",
                    region = "eu-west-1",
                    status = "stopped",
                    cpuUtilization = 0.0,
                    memoryUtilization = 0.0,
                    networkIn = 0.0,
                    networkOut = 0.0,
                    diskUsage = 45.7
                ),
                ServerStat(
                    instanceName = "cache-server-01",
                    instanceId = "i-fedcba0987654321",
                    instanceType = "m5.large",
                    region = "ap-southeast-1",
                    status = "running",
                    cpuUtilization = 12.8,
                    memoryUtilization = 34.2,
                    networkIn = 512.9,
                    networkOut = 256.4,
                    diskUsage = 23.1
                )
            )
            
            _serverStats.value = sampleStats
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
} 