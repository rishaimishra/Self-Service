package com.dpm.payment

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class NumberFormater {
 companion object{
     public  fun formatToTwoDecimalPlaces(number: Double): String {
         return String.format("%.2f", number)
     }

     fun formatAmount(value: Double): String {
         val formatter: NumberFormat = DecimalFormat.getNumberInstance(Locale.US).apply {
             minimumFractionDigits = 2
             maximumFractionDigits = 2
         }
         return formatter.format(value)
     }
 }
}