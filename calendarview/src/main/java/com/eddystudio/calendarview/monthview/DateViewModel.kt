package com.eddystudio.calendarview.monthview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DateViewModel(val myCalendar: Calendar, val currentMonth: Int) : ViewModel() {
    val dateObservable = MutableLiveData<String>()
    val isCurrentMonth = MutableLiveData<Boolean>()
    val isToday = MutableLiveData<Boolean>()

    init {
        dateObservable.postValue(myCalendar.get(Calendar.DAY_OF_MONTH).toString())
        isCurrentMonth.postValue(currentMonth == myCalendar.get(Calendar.MONTH))
        val calendar = Calendar.getInstance()
        isToday.postValue(calendar.get(Calendar.DATE) == myCalendar.get(Calendar.DATE)
            && calendar.get(Calendar.MONTH) == myCalendar.get(Calendar.MONTH)
            && calendar.get(Calendar.YEAR) == myCalendar.get(Calendar.YEAR))
    }
}