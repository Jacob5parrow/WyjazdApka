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
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kalendarz = findViewById<CalendarView>(R.id.calendarView)

        kalendarz.minDate = System.currentTimeMillis()
        kalendarz.maxDate = LocalDate.now().plusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        kalendarz.firstDayOfWeek = Calendar.MONDAY

        val start = findViewById<Button>(R.id.start)
        val end = findViewById<Button>(R.id.end)
        val wybData = arrayListOf(MsToDate(kalendarz.date)[0], MsToDate(kalendarz.date)[1], MsToDate(kalendarz.date)[2])

        val startWyj = mutableListOf(0, 0, 0)
        val endWyj = mutableListOf(0, 0, 0)

        kalendarz.setOnDateChangeListener { CalendarView, day, month, year ->
            wybData[0] = day
            wybData[1] = month
            wybData[2] = year
        }

        val textStart = findViewById<TextView>(R.id.textStart)
        val textEnd = findViewById<TextView>(R.id.textEnd)

        start.setOnClickListener {
            startWyj[0] = wybData[0]
            startWyj[1] = wybData[1]
            startWyj[2] = wybData[2]
            textStart.text = "Start wyjazdu: "+wybData[0].toString()+"/"+wybData[1].toString()+"/"+wybData[2].toString()
        }

        end.setOnClickListener {
            endWyj[0] = wybData[0]
            endWyj[1] = wybData[1]
            endWyj[2] = wybData[2]
            textEnd.text = "Koniec wyjazdu: "+wybData[0].toString()+"/"+wybData[1].toString()+"/"+wybData[2].toString()
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun MsToDate(milli : Long) : List<Int>
{
    val date = Date(milli)
    val form = SimpleDateFormat("yyyy/MM/dd")
    val formDate = form.format(date)
    val gData = formDate.split("/").map{ it.toInt() }
    return gData
}

