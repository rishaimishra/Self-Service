package com.dpm.payment.models.cep

class ComplaintsModel(@JvmField val complaintsTitle: String, @JvmField val complaintsIcon: Int,
    val information :String,
    val reason :List<String>,
    )
