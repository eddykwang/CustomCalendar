package com.eddystudio.calendarview.monthview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eddystudio.calendarview.BR
import com.eddystudio.calendarview.R
import com.eddystudio.quickrecyclerviewadapterlib.QuickRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

class MonthView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attributeSet, defStyle) {

    private val recyclerView: RecyclerView
    private val monthTv: TextView
    private val monTv: TextView
    private val tueTv: TextView
    private val wenTv: TextView
    private val thurTv: TextView
    private val friTv: TextView
    private val satTv: TextView
    private val sunTv: TextView

    private var month: Int
    private var year: Int
    lateinit var viewPool: RecyclerView.RecycledViewPool

    init {
        inflate(context, R.layout.layout_month_view, this)
        recyclerView = findViewById(R.id.month_recycler_view)
        monthTv = findViewById(R.id.month_of_year_tv)
        monTv = findViewById(R.id.mon_tv)
        tueTv = findViewById(R.id.tue_tv)
        wenTv = findViewById(R.id.wed_tv)
        thurTv = findViewById(R.id.thur_tv)
        friTv = findViewById(R.id.fri_tv)
        satTv = findViewById(R.id.sat_tv)
        sunTv = findViewById(R.id.sun_tv)

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

        while (dateList.size < 42) {

            if (dateList.size == 35 && dateList[34].myCalendar.get(Calendar.MONTH) > currentMonthCal.get(Calendar.MONTH)) {
                break
            }

            dateList.add(DateViewModel(myCalendar, month).apply {
                val vmc = this
                onclickListener = object : DateViewModel.Events {
                    override fun onClicked(view: View, calendar: Calendar) {
                        vmc.isSelected.set(true)
                        Toast.makeText(context, calendar.time.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            })
            myCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        recyclerView.apply {
            adapter = QuickRecyclerViewAdapter<DateViewModel>(
                dateList,
                R.layout.layout_date,
                BR.vmdate
            )
            setRecycledViewPool(viewPool)
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context, 7)
        }
    }

    fun setSmallView() {
        monthTv.visibility = View.GONE
        monTv.text = "M"
        tueTv.text = "T"
        wenTv.text = "W"
        thurTv.text = "T"
        friTv.text = "F"
        satTv.text = "S"
        sunTv.text = "S"
    }

    class Builder(private val monthView: MonthView) {

        fun build() {
            monthView.createView()
        }

        fun setYearAndMonth(year: Int, month: Int): Builder {
            monthView.setYearAndMonth(year, month)
            return this
        }

        fun setViewPool(viewPool: RecyclerView.RecycledViewPool): Builder {
            monthView.viewPool = viewPool
            return this
        }
    }

}