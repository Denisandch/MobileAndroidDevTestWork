package com.ntpro.mobileandroiddevtestwork

import com.ntpro.mobileandroiddevtestwork.room.DealDB

fun Server.Deal.toDeal(): Deal {
    return Deal(
        id, timeStamp.toString(), instrumentName, price, amount, side.toServerDealSide()
    ) //TODO FIX Date
}

fun Server.Deal.Side.toServerDealSide(): Deal.Side {
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
    return Deal(id, timeStamp, instrumentName, price, amount, side.toServerDealSide()) //TODO FIX Date
}

fun DealDB.Side.toServerDealSide(): Deal.Side {
    return when (this.name) {
        DealDB.Side.SELL.name -> Deal.Side.SELL
        else -> Deal.Side.BUY
    }
}