package com.ntpro.mobileandroiddevtestwork

import com.ntpro.mobileandroiddevtestwork.room.DealDB
import kotlin.math.roundToInt

fun Server.Deal.toDeal(): Deal {
    return Deal(
        id = id,
        timeStamp = timeStamp.toString(),
        instrumentName = instrumentName,
        price = (price * 100.0).roundToInt() / 100.0,
        amount = amount.roundToInt(),
        side = side.toDealSide()
    ) //TODO FIX Date
}

fun Server.Deal.Side.toDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}

fun Deal.toDealDB(): DealDB {
    return DealDB(id, timeStamp, instrumentName, price, amount, side.toDealDBSide())
}

fun Deal.Side.toDealDBSide(): DealDB.Side {
    return when (this.name) {
        Server.Deal.Side.SELL.name -> DealDB.Side.SELL
        else -> DealDB.Side.BUY
    }
}

fun DealDB.toDeal(): Deal {
    return Deal(id, timeStamp, instrumentName, price, amount, side.toDealSide()) //TODO FIX Date
}

fun DealDB.Side.toDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}