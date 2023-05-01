package com.ntpro.mobileandroiddevtestwork

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ntpro.mobileandroiddevtestwork.room.DealDB
import com.ntpro.mobileandroiddevtestwork.room.DealDao

typealias DealsLoader = suspend (page: Int, pageSize: Int) -> List<Deal>

class DealPagingSource(
    private val dealsLoader: DealsLoader,
) : PagingSource<Int, Deal>() {
    override fun getRefreshKey(state: PagingState<Int, Deal>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deal> {
        val page: Int = params.key ?: 0
        val pageSize: Int = params.loadSize.coerceAtMost(500)

        return try {
            val response = dealsLoader.invoke(page, pageSize)

            val nextKey = if (response.size < pageSize) null else page + 1
            val prevKey = if (page == 0) null else page - 1

            LoadResult.Page(response, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}