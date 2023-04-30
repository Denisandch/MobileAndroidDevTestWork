package com.ntpro.mobileandroiddevtestwork

import java.util.*

data class Deal(
    val id: Long,
    val timeStamp: String, //TODO Date
    val instrumentName: String,
    val price: Double,
    val amount: Double,
    val side: Side,
) {
    enum class Side {
        SELL, BUY
    }
}