package com.example.ongc.ui.serverstats

data class ServerStat(
    val instanceName: String,
    val instanceId: String,
    val instanceType: String,
    val region: String,
    val status: String,
    val cpuUtilization: Double,
    val memoryUtilization: Double,
    val networkIn: Double,
    val networkOut: Double,
    val diskUsage: Double
) 