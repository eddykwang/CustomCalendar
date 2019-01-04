package com.eddystudio.customcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eddystudio.monthview.MonthView

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val monthView = findViewById<MonthView>(R.id.month_view)
    val monthView2 = findViewById<MonthView>(R.id.month_view2)
    val monthView3 = findViewById<MonthView>(R.id.month_view3)
    val monthView4 = findViewById<MonthView>(R.id.month_view4)
    val monthView5 = findViewById<MonthView>(R.id.month_view5)

    MonthView.Builder(monthView)
//        .setYearAndMonth(2019, 1)
        .build()
    MonthView.Builder(monthView2)
        .setYearAndMonth(2019, 2)
      .build()
    MonthView.Builder(monthView3)
        .setYearAndMonth(2019, 3)
      .build()
    MonthView.Builder(monthView4)
        .setYearAndMonth(2019, 4)
      .build()
    MonthView.Builder(monthView5)
        .setYearAndMonth(2019, 5)
      .build()
  }
}
