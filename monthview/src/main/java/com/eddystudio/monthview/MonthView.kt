package com.eddystudio.monthview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eddystudio.quickrecyclerviewadapterlib.QuickRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

class MonthView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attributeSet, defStyle) {

  private val recyclerView: RecyclerView
  private val monthTv: TextView
  private var month: Int
  private var year: Int

  init {
    inflate(context, R.layout.layout_month_view, this)
    recyclerView = findViewById(R.id.month_recycler_view)
    monthTv = findViewById(R.id.month_of_year_tv)

    val calendar = Calendar.getInstance()
    month = calendar.get(Calendar.MONTH)
    year = calendar.get(Calendar.YEAR)
  }

  private fun setYearAndMonth(year: Int, month: Int) {
    this.year = year
    this.month = month
  }

  private fun createView() {
    val dateList = ArrayList<DateViewModel>()
    val myCalendar = Calendar.getInstance().apply {
      set(Calendar.YEAR, year)
      set(Calendar.MONTH, month)
      set(Calendar.DAY_OF_MONTH, 1)
    }
    val currentMonthCal = myCalendar.clone() as Calendar

    val simpleDateFormat = SimpleDateFormat("MMM yyyy", Locale("en", "US"))
    monthTv.text = simpleDateFormat.format(myCalendar.time)

    val monthBeginningCell = myCalendar.get(Calendar.DAY_OF_WEEK) - 1
    myCalendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

    while(dateList.size < 42) {

      if(dateList.size == 35 && dateList[34].myCalendar.get(Calendar.MONTH) > currentMonthCal.get(Calendar.MONTH) ){
        break
      }

      dateList.add(DateViewModel(myCalendar, month))
      myCalendar.add(Calendar.DAY_OF_MONTH, 1)
    }

    recyclerView.apply {
      adapter = QuickRecyclerViewAdapter<DateViewModel>(dateList, R.layout.layout_date, BR.vmdate)
      layoutManager =  GridLayoutManager(context, 7)
    }
  }

  class Builder(private val monthView: MonthView) {

    fun build() {
      monthView.createView()
    }

    fun setYearAndMonth(year: Int, month: Int): Builder {
      monthView.setYearAndMonth(year, month - 1)
      return this
    }
  }

}