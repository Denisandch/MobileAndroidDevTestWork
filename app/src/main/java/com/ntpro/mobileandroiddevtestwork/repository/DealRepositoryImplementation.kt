package com.ntpro.mobileandroiddevtestwork.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.*
import com.ntpro.mobileandroiddevtestwork.room.DealDao
import com.ntpro.mobileandroiddevtestwork.room.DealDataBase
import kotlinx.coroutines.flow.Flow


class DealRepositoryImplementation(context: Context) : DealRepository {

    init {
        context.deleteDatabase("deal_database")
    }

    private val dealDao: DealDao = DealDataBase.getDatabase(context).dealDao()
    override fun getDeals(filter: Column, isAsc: Boolean, maxID: Long): Flow<PagingData<Deal>> {

        val dealsLoader: DealsLoader = { page, pageSize ->
            selectQuery(page, pageSize, filter, isAsc, maxID)
        }

        return Pager<Int, Deal>(
            PagingConfig(
                pageSize = 500,
                prefetchDistance = 500
            ),
        ) {
            DealPagingSource(dealsLoader)
        }.flow
    }

    override suspend fun addNewDeals(deals: List<Server.Deal>) {
        dealDao.addListDeal(deals.map { it.toDealDB() })
    }
    private suspend fun selectQuery(
        page: Int,
        pageSize: Int,
        column: Column,
        direction: Boolean,
        maxID: Long
    ): List<Deal> {

        return if(direction) {
            dealDao.getAllDealsAsc(pageSize, page * pageSize, maxID, column.columnNameDB)
        } else {
            dealDao.getAllDealsDesc(pageSize, page * pageSize, maxID, column.columnNameDB)
        }.map { it.toDeal() }
    }
}