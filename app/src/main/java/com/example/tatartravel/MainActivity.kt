package com.example.tatartravel

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OrderStorage.loadOrders(this)

        val fromCity = findViewById<EditText>(R.id.fromCity)
        val toCity = findViewById<EditText>(R.id.toCity)
        val date = findViewById<EditText>(R.id.date)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val ordersMainButton = findViewById<Button>(R.id.ordersMainButton)

        date.setOnClickListener {
            showDatePicker(date)
        }

        date.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showDatePicker(date)
            }
        }

        searchButton.setOnClickListener {
            val from = fromCity.text.toString().trim()
            val to = toCity.text.toString().trim()
            val flightDate = date.text.toString().trim()

            if (from.isEmpty() || to.isEmpty() || flightDate.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else if (from.equals(to, ignoreCase = true)) {
                Toast.makeText(this, "Пункты отправления и прибытия не должны совпадать", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FlightsActivity::class.java)
                intent.putExtra("fromCity", from)
                intent.putExtra("toCity", to)
                intent.putExtra("date", flightDate)
                startActivity(intent)
            }
        }

        ordersMainButton.setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }
    }

    private fun showDatePicker(dateField: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDay = String.format("%02d", selectedDay)
                val formattedMonth = String.format("%02d", selectedMonth + 1)
                val formattedDate = "$formattedDay.$formattedMonth.$selectedYear"
                dateField.setText(formattedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }
}