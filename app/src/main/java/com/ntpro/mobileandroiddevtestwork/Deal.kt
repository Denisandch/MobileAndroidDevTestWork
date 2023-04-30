package com.ntpro.mobileandroiddevtestwork

import java.util.*

data class Deal(
    val id: Long,
    val timeStamp: Date,
    val instrumentName: String,
    val price: Double,
    val amount: Int,
    val side: Side,
) {
    enum class Side {
        SELL, BUY
    }
}