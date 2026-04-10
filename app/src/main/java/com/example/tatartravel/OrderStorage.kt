package com.example.tatartravel

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrderStorage {

    private const val PREFS_NAME = "orders_prefs"
    private const val ORDERS_KEY = "orders_key"

    val orders = mutableListOf<Flight>()

    fun loadOrders(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(ORDERS_KEY, null)

        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Flight>>() {}.type
            val savedOrders: MutableList<Flight> = Gson().fromJson(json, type)
            orders.clear()
            orders.addAll(savedOrders)
        }
    }

    fun saveOrders(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(orders)
        editor.putString(ORDERS_KEY, json)
        editor.apply()
    }

    fun addOrder(context: Context, flight: Flight): Boolean {
        val exists = orders.any {
            it.fromCity == flight.fromCity &&
                    it.toCity == flight.toCity &&
                    it.date == flight.date &&
                    it.time == flight.time &&
                    it.price == flight.price
        }

        if (exists) {
            return false
        }

        orders.add(flight)
        saveOrders(context)
        return true
    }

    fun removeOrder(context: Context, flight: Flight) {
        orders.remove(flight)
        saveOrders(context)
    }
}