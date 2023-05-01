package com.ntpro.mobileandroiddevtestwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ntpro.mobileandroiddevtestwork.usecase.AddDataToDBUseCase
import com.ntpro.mobileandroiddevtestwork.usecase.GetNewDealsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class AppViewModel(
    private val getNewDealsUseCase: GetNewDealsUseCase,
    private val addDealsToDBUseCase: AddDataToDBUseCase
) : ViewModel() {

    private val filter = MutableLiveData<Column>()
    private var isAsc = false
    private var maxID: Long = 0

    private val _countOfNewDeals = MutableLiveData<Int>(0)
    val countOfNewDeals: LiveData<Int> = _countOfNewDeals

    init {
        Server().subscribeToDeals { dealsList ->
            _countOfNewDeals.value = _countOfNewDeals.value?.plus(dealsList.size)
            viewModelScope.launch {
                maxID += addDealsToDBUseCase.addNewDeals(dealsList)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val deals: Flow<PagingData<Deal>> =
        filter.asFlow().flatMapLatest {
            _countOfNewDeals.value = 0
            getNewDealsUseCase.getDeals(filter = filter.value!!, isAsc = this.isAsc, maxID)
        }.cachedIn(viewModelScope)

    fun setFilter(filter: Column, isAsc: Boolean) {
        this.isAsc = isAsc
        this.filter.value = filter
    }

}