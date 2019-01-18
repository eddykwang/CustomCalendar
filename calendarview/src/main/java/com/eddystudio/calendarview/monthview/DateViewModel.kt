package com.eddystudio.calendarview.monthview

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eddystudio.calendarview.R
import java.util.*

class DateViewModel(val myCalendar: Calendar, val currentMonth: Int) : ViewModel() {
    val dateObservable = ObservableField<String>()
    val isCurrentMonth = ObservableField<Boolean>()
    val isToday = ObservableField<Boolean>()
    val isSelected = ObservableField<Boolean>()
    var onclickListener: Events? = null

    init {
        isSelected.set(false)
        dateObservable.set( myCalendar.get(Calendar.DAY_OF_MONTH).toString())
        isCurrentMonth.set( currentMonth == myCalendar.get(Calendar.MONTH))
        val calendar = Calendar.getInstance()
        isToday.set( calendar.get(Calendar.DATE) == myCalendar.get(Calendar.DATE)
            && calendar.get(Calendar.MONTH) == myCalendar.get(Calendar.MONTH)
            && calendar.get(Calendar.YEAR) == myCalendar.get(Calendar.YEAR))
    }


    fun onClicked(view: View) {
        if (onclickListener != null && isCurrentMonth.get()!!) {
            onclickListener!!.onClicked(view, myCalendar)
        }
    }

    interface Events {
        fun onClicked(view: View, calendar: Calendar)
    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["isToday", "isSelected"], requireAll = true)
        fun setBackgroundDrawable(textView: TextView, isToday: Boolean, isSelectedDate: Boolean) {
            if (isToday) {
                textView.setBackgroundResource(R.drawable.bg_today)
            }

            if (isSelectedDate) {
                textView.setBackgroundResource(R.drawable.bg_selected_date_from)
            } else {
                if (!isToday) {
                    textView.setBackgroundResource(0)
                }
            }
        }
    }
}