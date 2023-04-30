package com.ntpro.mobileandroiddevtestwork

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ntpro.mobileandroiddevtestwork.repository.DealRepository
import com.ntpro.mobileandroiddevtestwork.room.DealDB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

val DEFAULT_FILTER = Column.INSTRUMENT_NAME
class AppViewModel(
    private val dealRepository: DealRepository
) : ViewModel() {

    private val filter = MutableLiveData<Column>()
    private var isAsc = false

    private val _countOfNewDeals = MutableLiveData<Int>(0)
    val countOfNewDeals: LiveData<Int> = _countOfNewDeals

    init {
        Server().subscribeToDeals { dealsList ->
            _countOfNewDeals.value = _countOfNewDeals.value?.plus(dealsList.size)
            viewModelScope.launch {
                dealRepository.addNewDeals(dealsList.map { it.toDeal() })
            }
        }
        filter.value = DEFAULT_FILTER
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val deals: Flow<PagingData<Deal>> =
        filter.asFlow().flatMapLatest {
            _countOfNewDeals.value = 0
            dealRepository.getDeals(filter = filter.value!!, isAsc = this.isAsc)
        }.cachedIn(viewModelScope)

    fun setFilter(filter: Column, isAsc: Boolean) {
        this.isAsc = isAsc
        this.filter.value = filter
    }

}