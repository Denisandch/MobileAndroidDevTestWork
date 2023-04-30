package com.ntpro.mobileandroiddevtestwork

import android.util.Log
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

class AppViewModel(
    private val dealRepository: DealRepository
) : ViewModel() {

    private val filter = MutableLiveData("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val deals: Flow<PagingData<Deal>> =
        filter.asFlow().flatMapLatest { dealRepository.getDeals() }.cachedIn(viewModelScope)


    init {
        Server().subscribeToDeals {
            viewModelScope.launch {
                dealRepository.addNewDeals(it.map { it.toDeal() })
            }
        }
    }

    fun setFilter(filter: String, isAsc: Boolean) {
        this.filter.value = filter
    }

}