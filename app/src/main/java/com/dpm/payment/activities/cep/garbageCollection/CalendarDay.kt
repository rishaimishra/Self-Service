package com.dpm.payment.activities.cep.garbageCollection

import java.util.Calendar

data class CalendarDay(
    val date: Int,
    val isAvailable: Boolean,
    val calender: Calendar
)
