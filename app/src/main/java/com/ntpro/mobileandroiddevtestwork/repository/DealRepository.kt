package com.ntpro.mobileandroiddevtestwork.repository

import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.Column
import com.ntpro.mobileandroiddevtestwork.Deal
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    fun getDeals(filter: Column, isAsc: Boolean): Flow<PagingData<Deal>>

    suspend fun addNewDeals(deals: List<Deal>)
}