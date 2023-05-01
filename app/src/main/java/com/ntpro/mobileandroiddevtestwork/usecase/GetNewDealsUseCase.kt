package com.ntpro.mobileandroiddevtestwork.usecase

import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.Column
import com.ntpro.mobileandroiddevtestwork.Deal
import com.ntpro.mobileandroiddevtestwork.repository.DealRepository
import kotlinx.coroutines.flow.Flow

class GetNewDealsUseCase(
    private val repository: DealRepository
) {
    fun getDeals(filter: Column, isAsc: Boolean, maxID: Long): Flow<PagingData<Deal>> {
        return repository.getDeals(filter, isAsc, maxID)
    }
}