package com.ntpro.mobileandroiddevtestwork.repository

import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.Deal
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.room.DealDB
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    fun getDeals(): Flow<PagingData<Deal>>

    suspend fun addNewDeals(deals: List<Deal>)
}