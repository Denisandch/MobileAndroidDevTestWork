package com.ntpro.mobileandroiddevtestwork

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ntpro.mobileandroiddevtestwork.room.DealDao

class DealPagingSource(
    private val dealDao: DealDao,
    private val column: Column,
    private val direction: Boolean,
    private val maxID: Long
) : PagingSource<Int, Deal>() {
    override fun getRefreshKey(state: PagingState<Int, Deal>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deal> {
        val page: Int = params.key ?: 0
        val pageSize: Int = params.loadSize.coerceAtMost(50)

        return try {
            val response = when (column) {
                Column.INSTRUMENT_NAME -> {
                    if (direction) {
                        dealDao.getAllDealsByInstrumentNameAsc(pageSize, page * pageSize, maxID)
                    } else {
                        dealDao.getAllDealsByInstrumentNameDesc(pageSize, page * pageSize, maxID)
                    }
                }
                Column.TIME_STAMP -> {
                    if (direction) {
                        dealDao.getAllDealsByDateAsc(pageSize, page * pageSize, maxID)
                    } else {
                        dealDao.getAllDealsByDateDesc(pageSize, page * pageSize, maxID)
                    }
                }
                Column.PRICE -> {
                    if (direction) {
                        dealDao.getAllDealsByPriceAsc(pageSize, page * pageSize, maxID)
                    } else {
                        dealDao.getAllDealsByPriceDesc(pageSize, page * pageSize, maxID)
                    }
                }
                Column.AMOUNT -> {
                    if (direction) {
                        dealDao.getAllDealsByAmountAsc(pageSize, page * pageSize, maxID)
                    } else {
                        dealDao.getAllDealsByAmountDesc(pageSize, page * pageSize, maxID)
                    }
                }
                Column.SIDE -> {
                    if (direction) {
                        dealDao.getAllDealsBySideAsc(pageSize, page * pageSize, maxID)
                    } else {
                        dealDao.getAllDealsBySideDesc(pageSize, page * pageSize, maxID)
                    }
                }
            }.map { it.toDeal() }


            val nextKey = if (response.size < pageSize) null else page + 1
            val prevKey = if (page == 0) null else page - 1

            LoadResult.Page(response, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}