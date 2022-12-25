package com.example.wyjazdapka

import android.os.Build
import android.os.Bundle
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kalendarz = findViewById<CalendarView>(R.id.calendarView)

        kalendarz.minDate = System.currentTimeMillis()
        kalendarz.maxDate = LocalDate.now().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        kalendarz.firstDayOfWeek = Calendar.MONDAY
    }
}