package com.ntpro.mobileandroiddevtestwork

import com.ntpro.mobileandroiddevtestwork.room.DealDB
import java.util.*
import kotlin.math.roundToInt

fun Server.Deal.toDealDB(): DealDB {
    return DealDB(
        id = id,
        timeStamp = timeStamp.time,
        instrumentName = instrumentName,
        price = (price * 100.0).roundToInt() / 100.0,
        amount = amount.roundToInt(),
        side = side.toDealDBSide()
    )
}

fun Server.Deal.Side.toDealDBSide(): DealDB.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> DealDB.Side.SELL
        else -> DealDB.Side.BUY
    }
}

fun DealDB.toDeal(): Deal {
    return Deal(
        id = id,
        timeStamp = Date(timeStamp),
        instrumentName = instrumentName,
        price = price,
        amount = amount,
        side = side.toDealSide()
    )
}

fun DealDB.Side.toDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}