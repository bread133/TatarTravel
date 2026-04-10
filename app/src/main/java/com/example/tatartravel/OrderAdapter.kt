package com.example.tatartravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    private val orders: List<Flight>,
    private val onDeleteClick: (Flight) -> Unit
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val orderRouteText: TextView = itemView.findViewById(R.id.orderRouteText)
        val orderDateTimeText: TextView = itemView.findViewById(R.id.orderDateTimeText)
        val orderPriceText: TextView = itemView.findViewById(R.id.orderPriceText)
        val deleteOrderButton: Button = itemView.findViewById(R.id.deleteOrderButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.orderRouteText.text = "${order.fromCity} → ${order.toCity}"
        holder.orderDateTimeText.text = "Дата: ${order.date}   Время: ${order.time}"
        holder.orderPriceText.text = "Цена: ${order.price}"

        holder.deleteOrderButton.setOnClickListener {
            onDeleteClick(order)
        }
    }

    override fun getItemCount(): Int = orders.size
}