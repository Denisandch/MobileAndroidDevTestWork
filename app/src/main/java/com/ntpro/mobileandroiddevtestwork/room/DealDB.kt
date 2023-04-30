package com.ntpro.mobileandroiddevtestwork.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "deal", indices = [Index(value = ["id"])])
data class DealDB(
    @PrimaryKey val id: Long,
    val timeStamp: Long,
    val instrumentName: String,
    val price: Double,
    val amount: Int,
    val side: Side,
) {
    enum class Side {
        SELL, BUY
    }
}
