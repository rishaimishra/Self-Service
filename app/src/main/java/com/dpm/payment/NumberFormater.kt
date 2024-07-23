package com.dpm.payment

class NumberFormater {
 companion object{
     public  fun formatToTwoDecimalPlaces(number: Double): String {
         return String.format("%.2f", number)
     }
 }
}