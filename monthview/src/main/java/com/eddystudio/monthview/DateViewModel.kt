package com.eddystudio.monthview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DateViewModel(val date: Date, val currentMonth: Int) : ViewModel() {
  val dateObservable = MutableLiveData<String>()
  val isCurrentMonth = MutableLiveData<Boolean>()
  val isToday = MutableLiveData<Boolean>()

  init {
    dateObservable.postValue(date.date.toString())
    isCurrentMonth.postValue(currentMonth == date.month)
    isToday.postValue(Calendar.getInstance().get(Calendar.DATE) == date.date)
  }
}