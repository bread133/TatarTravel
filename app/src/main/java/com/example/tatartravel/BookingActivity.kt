package com.example.tatartravel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val bookingInfo = findViewById<TextView>(R.id.bookingInfo)
        val bookButton = findViewById<Button>(R.id.bookButton)
        val myOrdersButton = findViewById<Button>(R.id.myOrdersButton)

        val fromCity = intent.getStringExtra("fromCity") ?: ""
        val toCity = intent.getStringExtra("toCity") ?: ""
        val date = intent.getStringExtra("date") ?: ""
        val time = intent.getStringExtra("time") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        val flight = Flight(fromCity, toCity, date, time, price)

        bookingInfo.text = """
        Маршрут: $fromCity → $toCity
        
        Дата: $date
        Время: $time
        
        Цена: $price
        Место: 1
        """.trimIndent()

        bookButton.setOnClickListener {
            val added = OrderStorage.addOrder(this, flight)

            if (added) {
                Toast.makeText(this, "Бронирование успешно выполнено", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, OrdersActivity::class.java))
            } else {
                Toast.makeText(this, "Этот рейс уже забронирован", Toast.LENGTH_SHORT).show()
            }
        }

        myOrdersButton.setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }
    }
}