package com.example.tatartravel

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit

object OrderStorage {

    private const val PREFS_NAME = "orders_prefs"
    private const val ORDERS_KEY = "orders_key"

    val orders = mutableListOf<SearchOptions>()

    fun loadOrders(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(ORDERS_KEY, null)

        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<SearchOptions>>() {}.type
            val savedOrders: MutableList<SearchOptions> = Gson().fromJson(json, type)
            orders.clear()
            orders.addAll(savedOrders)
        }
    }

    fun saveOrders(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            val json = Gson().toJson(orders)
            putString(ORDERS_KEY, json)
        }
    }

    fun addOrder(context: Context, searchOptions: SearchOptions): Boolean {
        val exists = orders.any {
            it.fromCity == searchOptions.fromCity &&
                    it.toCity == searchOptions.toCity &&
                    it.date == searchOptions.date &&
                    it.time == searchOptions.time &&
                    it.price == searchOptions.price
        }

        if (exists) {
            return false
        }

        orders.add(searchOptions)
        saveOrders(context)
        return true
    }

    fun removeOrder(context: Context, searchOptions: SearchOptions) {
        orders.remove(searchOptions)
        saveOrders(context)
    }
}