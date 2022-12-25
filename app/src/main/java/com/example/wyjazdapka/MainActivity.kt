package com.example.wyjazdapka

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kalendarz = findViewById<CalendarView>(R.id.calendarView)

        kalendarz.minDate = System.currentTimeMillis()
        kalendarz.maxDate =
            LocalDate.now().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant()
                .toEpochMilli()
        kalendarz.firstDayOfWeek = Calendar.MONDAY

        val start = findViewById<Button>(R.id.start)
        val end = findViewById<Button>(R.id.end)
        val wybData = arrayListOf(
            msToDate(kalendarz.date)[0],
            msToDate(kalendarz.date)[1],
            msToDate(kalendarz.date)[2]
        )

        val startWyjAsList = mutableListOf(0, 0, 0)
        val endWyjAsList = mutableListOf(0, 0, 0)

        kalendarz.setOnDateChangeListener { _, day, month, year ->
            wybData[0] = day
            wybData[1] = month
            wybData[2] = year
        }


        val textStart = findViewById<TextView>(R.id.textStart)
        val textEnd = findViewById<TextView>(R.id.textEnd)
        val textDni = findViewById<TextView>(R.id.textDni)


        start.setOnClickListener {
            startWyjAsList[0] = wybData[0]
            startWyjAsList[1] = wybData[1]
            startWyjAsList[2] = wybData[2]
            textStart.text =
                "Start wyjazdu: " + wybData[0].toString() + "/" + wybData[1].toString() + "/" + wybData[2].toString()


        }

        end.setOnClickListener {
            endWyjAsList[0] = wybData[0]
            endWyjAsList[1] = wybData[1]
            endWyjAsList[2] = wybData[2]
            textEnd.text =
                "Koniec wyjazdu: " + wybData[0].toString() + "/" + wybData[1].toString() + "/" + wybData[2].toString()

            val startWyj = LocalDate.of(startWyjAsList[0], startWyjAsList[1], startWyjAsList[2])
            val endWyj = LocalDate.of(endWyjAsList[0], endWyjAsList[1], endWyjAsList[2])

            val period = ChronoUnit.DAYS.between(startWyj, endWyj)
            val days = abs(period)

            textDni.text = "Dni wyjazdu: $days"

        }
    }
}



@SuppressLint("SimpleDateFormat")
fun msToDate(milli: Long): List<Int> {
    val date = Date(milli)
    val form = SimpleDateFormat("yyyy/MM/dd")
    val formDate = form.format(date)
    return formDate.split("/").map { it.toInt() }
}

