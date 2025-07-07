package com.example.ongc.ui.qrscanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRScannerViewModel : ViewModel() {

    private val _scannedText = MutableLiveData<String>()
    val scannedText: LiveData<String> = _scannedText

    private val _isScanning = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isScanning: LiveData<Boolean> = _isScanning

    fun setScannedText(text: String) {
        _scannedText.value = text
    }

    fun setScanning(scanning: Boolean) {
        _isScanning.value = scanning
    }
} 