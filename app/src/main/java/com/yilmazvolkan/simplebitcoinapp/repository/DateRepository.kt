package com.yilmazvolkan.simplebitcoinapp.repository

import android.content.Context
import android.util.Log
import com.yilmazvolkan.simplebitcoinapp.BitcoinApplication
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class DateRepository {

    @Inject
    lateinit var bitcoinApplication: BitcoinApplication

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun updateTodayDate() {
        val currentTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

        val sharedPreference =
            bitcoinApplication.getSharedPreferences(PREFERENCE_HOLDER_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString(EDITOR_HOLDER_NAME, formatter.format(currentTime))
        editor.apply()

        Log.d("Day", "Day updated.")
    }

    fun checkDates(): Boolean {
        val currentTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val currentDate = formatter.format(currentTime)

        val sharedPreference =
            bitcoinApplication.getSharedPreferences(PREFERENCE_HOLDER_NAME, Context.MODE_PRIVATE)
        val checkedDate = sharedPreference.getString(EDITOR_HOLDER_NAME, "")

        Log.d("Day", "Current: $currentDate")
        Log.d("Day", "Checked: $checkedDate")
        return currentDate == checkedDate
    }

    companion object {
        private const val PREFERENCE_HOLDER_NAME = "LAST_UPDATE"
        private const val EDITOR_HOLDER_NAME = "UPDATE_DAY"
        private const val DATE_FORMAT = "MM/dd/yyyy"
    }
}
