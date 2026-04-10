package com.example.tatartravel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchOptionsActivity : AppCompatActivity() {

    private lateinit var searchOptions: List<SearchOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flights)

        val titleText = findViewById<TextView>(R.id.titleFlights)
        val flightsRecyclerView = findViewById<RecyclerView>(R.id.flightsRecyclerView)

        val fromCity = intent.getStringExtra("fromCity") ?: ""
        val toCity = intent.getStringExtra("toCity") ?: ""
        val date = intent.getStringExtra("date") ?: ""

        titleText.text = "Найденные рейсы"

        searchOptions = listOf(
            SearchOptions(fromCity, toCity, date, "08:00", "1200 ₽"),
            SearchOptions(fromCity, toCity, date, "12:30", "950 ₽"),
            SearchOptions(fromCity, toCity, date, "18:15", "1100 ₽")
        )

        val adapter = SearchSystemAdapter(searchOptions) { selectedFlight ->
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("fromCity", selectedFlight.fromCity)
            intent.putExtra("toCity", selectedFlight.toCity)
            intent.putExtra("date", selectedFlight.date)
            intent.putExtra("time", selectedFlight.time)
            intent.putExtra("price", selectedFlight.price)
            startActivity(intent)
        }

        flightsRecyclerView.layoutManager = LinearLayoutManager(this)
        flightsRecyclerView.adapter = adapter
    }
}