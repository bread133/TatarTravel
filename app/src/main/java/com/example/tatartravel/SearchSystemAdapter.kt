package com.example.tatartravel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchSystemAdapter(
    private val searchOptions: List<SearchOptions>,
    private val onItemClick: (SearchOptions) -> Unit
) : RecyclerView.Adapter<SearchSystemAdapter.SearchOptionsViewHolder>() {

    class SearchOptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routeText: TextView = itemView.findViewById(R.id.routeText)
        val dateTimeText: TextView = itemView.findViewById(R.id.dateTimeText)
        val priceText: TextView = itemView.findViewById(R.id.priceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchOptionsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flight, parent, false)
        return SearchOptionsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SearchOptionsViewHolder, position: Int) {
        val flight = searchOptions[position]

        holder.routeText.text = "${flight.fromCity} → ${flight.toCity}"
        holder.dateTimeText.text = "Дата: ${flight.date}   Время: ${flight.time}"
        holder.priceText.text = "Цена: ${flight.price}"

        holder.itemView.setOnClickListener {
            onItemClick(flight)
        }
    }

    override fun getItemCount(): Int = searchOptions.size
}