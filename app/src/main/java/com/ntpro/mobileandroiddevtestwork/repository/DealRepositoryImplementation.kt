package com.ntpro.mobileandroiddevtestwork.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.Deal
import com.ntpro.mobileandroiddevtestwork.DealPagingSource
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.room.DealDao
import com.ntpro.mobileandroiddevtestwork.room.DealDataBase
import com.ntpro.mobileandroiddevtestwork.toDealDB
import kotlinx.coroutines.flow.Flow

class DealRepositoryImplementation(context: Context) : DealRepository {

    init {
        context.deleteDatabase("deal_database")
    }

    private val dealDao: DealDao = DealDataBase.getDatabase(context).dealDao()
    //TODO comment about maxID
    /**
    *
    *
    */
    private var maxID: Long = 0
    override fun getDeals(): Flow<PagingData<Deal>> {
        return Pager<Int, Deal>(
            PagingConfig(pageSize = 30, initialLoadSize = 30),
        ) {
            DealPagingSource(dealDao)
        }.flow
    }

    override suspend fun addNewDeals(deals: List<Deal>) {
        maxID += deals.size - 1
        dealDao.addListDeal(deals.map { it.toDealDB() })
    }
}