package com.example.ongc.ui.files

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilesViewModel : ViewModel() {

    private val _files = MutableLiveData<List<FileItem>>()
    val files: LiveData<List<FileItem>> = _files

    private val _filteredFiles = MutableLiveData<List<FileItem>>()
    val filteredFiles: LiveData<List<FileItem>> = _filteredFiles

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    init {
        loadSampleFiles()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterFiles(query)
    }

    private fun filterFiles(query: String) {
        val allFiles = _files.value ?: emptyList()
        if (query.isEmpty()) {
            _filteredFiles.value = allFiles
        } else {
            val filtered = allFiles.filter { file ->
                file.name.contains(query, ignoreCase = true)
            }
            _filteredFiles.value = filtered
        }
    }

    private fun loadSampleFiles() {
        val sampleFiles = listOf(
            FileItem("Equipment", "/storage/emulated/0/Assets/Equipment", true),
            FileItem("Vehicles", "/storage/emulated/0/Assets/Vehicles", true),
            FileItem("Documents", "/storage/emulated/0/Assets/Documents", true),
            FileItem("equipment_report.pdf", "/storage/emulated/0/Assets/Documents/equipment_report.pdf", false, 1024000L),
            FileItem("vehicle_image.jpg", "/storage/emulated/0/Assets/Vehicles/vehicle_image.jpg", false, 2048000L),
            FileItem("maintenance_log.docx", "/storage/emulated/0/Assets/Documents/maintenance_log.docx", false, 512000L)
        )
        _files.value = sampleFiles
        _filteredFiles.value = sampleFiles
    }
} 