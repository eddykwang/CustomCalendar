package com.eddystudio.customcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eddystudio.monthview.MonthView

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val monthView = findViewById<MonthView>(R.id.month_view)
    MonthView.Builder(monthView)
//        .setYearAndMonth(2019, 1)
        .build()
  }
}
