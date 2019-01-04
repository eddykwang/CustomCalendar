package com.eddystudio.calendarview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eddystudio.calendarview.monthview.MonthView
import java.util.*

class CalendarViewAdapter(val list: ArrayList<Calendar>) :
    RecyclerView.Adapter<CalendarViewAdapter.CalendarViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_calendar_single_month, parent, false)
    return CalendarViewHolder(view as MonthView)
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
    holder.bind(list[position].get(Calendar.YEAR), list[position].get(Calendar.MONTH))
  }


  class CalendarViewHolder(val monthView: MonthView) : RecyclerView.ViewHolder(monthView) {

    fun bind(year: Int, month: Int) {
      MonthView.Builder(monthView)
          .setYearAndMonth(year, month)
          .build()
    }
  }
}