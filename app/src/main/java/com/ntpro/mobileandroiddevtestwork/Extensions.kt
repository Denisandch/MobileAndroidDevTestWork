package com.ntpro.mobileandroiddevtestwork

import com.ntpro.mobileandroiddevtestwork.room.DealDB
import java.util.*
import kotlin.math.roundToInt

fun Server.Deal.toDeal(): Deal {
    return Deal(
        id = id,
        timeStamp = timeStamp,
        instrumentName = instrumentName,
        price = (price * 100.0).roundToInt() / 100.0,
        amount = amount.roundToInt(),
        side = side.toDealSide()
    )
}

fun Server.Deal.Side.toDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}

fun Deal.toDealDB(): DealDB {
    return DealDB(id, timeStamp.time, instrumentName, price, amount, side.toDealDBSide())
}

fun Deal.Side.toDealDBSide(): DealDB.Side {
    return when (this.name) {
        Server.Deal.Side.SELL.name -> DealDB.Side.SELL
        else -> DealDB.Side.BUY
    }
}

fun DealDB.toDeal(): Deal {
    return Deal(id, Date(timeStamp), instrumentName, price, amount, side.toDealSide())
}

fun DealDB.Side.toDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}