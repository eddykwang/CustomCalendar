package com.eddystudio.calendarview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CalendarView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attributeSet, defStyle) {

    private val recyclerView: RecyclerView

    init {
        View.inflate(context, R.layout.layout_calendar_view, this)
        recyclerView = this.findViewById(R.id.calendar_recycler_view)

        val list = ArrayList<Calendar>()

        val myCalendar = Calendar.getInstance()

        while (list.size < 12) {
            list.add(myCalendar.clone() as Calendar)
            myCalendar.add(Calendar.MONTH, 1)
        }

        recyclerView.apply {
            adapter = CalendarViewAdapter(list)
            layoutManager = GridLayoutManager(context,1)
        }
    }

}