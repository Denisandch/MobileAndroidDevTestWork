package com.ntpro.mobileandroiddevtestwork.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "deal", indices = [Index(value = ["id"])])
data class DealDB(
    @PrimaryKey val id: Long,
    val timeStamp: String, //TODO replace to DATE
    val instrumentName: String,
    val price: Double,
    val amount: Double,
    val side: Side,
) {
    enum class Side {
        SELL, BUY
    }
}
