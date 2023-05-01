package com.ntpro.mobileandroiddevtestwork.usecase

import com.ntpro.mobileandroiddevtestwork.Deal
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.repository.DealRepository

class AddDataToDBUseCase(
    private val repository: DealRepository
) {
    suspend fun addNewDeals(deals: List<Server.Deal>): Int {
        repository.addNewDeals(deals)
        return deals.size - 1
    }
}