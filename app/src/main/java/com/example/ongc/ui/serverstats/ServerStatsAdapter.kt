package com.example.ongc.ui.serverstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ongc.R

class ServerStatsAdapter(
    private var serverStats: List<ServerStat>,
    private val onServerStatClick: (ServerStat) -> Unit
) : RecyclerView.Adapter<ServerStatsAdapter.ServerStatViewHolder>() {

    class ServerStatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val statusIcon: ImageView = view.findViewById(R.id.statusIcon)
        val instanceName: TextView = view.findViewById(R.id.instanceName)
        val instanceId: TextView = view.findViewById(R.id.instanceId)
        val instanceType: TextView = view.findViewById(R.id.instanceType)
        val region: TextView = view.findViewById(R.id.region)
        val status: TextView = view.findViewById(R.id.status)
        val cpuUtilization: TextView = view.findViewById(R.id.cpuUtilization)
        val memoryUtilization: TextView = view.findViewById(R.id.memoryUtilization)
        val networkIn: TextView = view.findViewById(R.id.networkIn)
        val networkOut: TextView = view.findViewById(R.id.networkOut)
        val diskUsage: TextView = view.findViewById(R.id.diskUsage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerStatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_server_stat, parent, false)
        return ServerStatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServerStatViewHolder, position: Int) {
        val serverStat = serverStats[position]
        
        holder.instanceName.text = serverStat.instanceName
        holder.instanceId.text = serverStat.instanceId
        holder.instanceType.text = serverStat.instanceType
        holder.region.text = serverStat.region
        holder.status.text = serverStat.status.capitalize()
        holder.cpuUtilization.text = "CPU: ${String.format("%.1f", serverStat.cpuUtilization)}%"
        holder.memoryUtilization.text = "Memory: ${String.format("%.1f", serverStat.memoryUtilization)}%"
        holder.networkIn.text = "In: ${String.format("%.1f", serverStat.networkIn)} MB/s"
        holder.networkOut.text = "Out: ${String.format("%.1f", serverStat.networkOut)} MB/s"
        holder.diskUsage.text = "Disk: ${String.format("%.1f", serverStat.diskUsage)}%"
        
        // Set status icon and color
        when (serverStat.status.lowercase()) {
            "running" -> {
                holder.statusIcon.setImageResource(R.drawable.ic_server_running)
                holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.status_running))
            }
            "stopped" -> {
                holder.statusIcon.setImageResource(R.drawable.ic_server_stopped)
                holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.status_stopped))
            }
            else -> {
                holder.statusIcon.setImageResource(R.drawable.ic_server_unknown)
                holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.status_unknown))
            }
        }
        
        holder.itemView.setOnClickListener {
            onServerStatClick(serverStat)
        }
    }

    override fun getItemCount() = serverStats.size

    fun updateServerStats(newServerStats: List<ServerStat>) {
        serverStats = newServerStats
        notifyDataSetChanged()
    }
} 