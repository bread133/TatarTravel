package com.example.tatartravel

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrdersActivity : AppCompatActivity() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var emptyOrdersText: TextView
    private lateinit var adapter: TicketsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val ordersTitle = findViewById<TextView>(R.id.ordersTitle)
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView)
        emptyOrdersText = findViewById(R.id.emptyOrdersText)

        ordersTitle.text = "Мои заказы"

        ordersRecyclerView.layoutManager = LinearLayoutManager(this)
        updateOrdersList()
    }

    private fun updateOrdersList() {
        if (OrderStorage.orders.isEmpty()) {
            emptyOrdersText.visibility = TextView.VISIBLE
            ordersRecyclerView.visibility = RecyclerView.GONE
        } else {
            emptyOrdersText.visibility = TextView.GONE
            ordersRecyclerView.visibility = RecyclerView.VISIBLE

            adapter = TicketsAdapter(OrderStorage.orders) { selectedOrder ->
                AlertDialog.Builder(this)
                    .setTitle("Удаление заказа")
                    .setMessage("Удалить выбранное бронирование?")
                    .setPositiveButton("Да") { _, _ ->
                        OrderStorage.removeOrder(this, selectedOrder)
                        updateOrdersList()
                        Toast.makeText(this, "Заказ удален", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Нет", null)
                    .show()
            }

            ordersRecyclerView.adapter = adapter
        }
    }
}