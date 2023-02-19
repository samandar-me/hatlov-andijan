package com.sdk.hatlovandijon.ui.bottom.chart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sdk.data.manager.FilterPagingSource
import com.sdk.data.network.main.MainService
import com.sdk.domain.model.VariableStatus
import com.sdk.domain.model.appeal.Murojaatlar
import com.sdk.domain.use_case.base.AllUseCases
import com.sdk.domain.util.Status
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val mainService: MainService,
    private val allUseCases: AllUseCases
) : ViewModel() {

    var variableList: MutableStateFlow<List<VariableStatus>> = MutableStateFlow(mutableListOf())

    init {
        getAndSaveVariable()
    }

    fun filterAppeals(query: HashMap<String, Any>): Flow<PagingData<Murojaatlar>> {
        return Pager(
            config = PagingConfig(pageSize = 9),
            pagingSourceFactory = { FilterPagingSource(mainService, query) }
        ).flow.cachedIn(viewModelScope)
    }
    private fun getAndSaveVariable() {
        viewModelScope.launch {
            allUseCases.getVariableUseCase.invoke(Unit).collect { status ->
                when (status) {
                    is Status.Loading -> Unit
                    is Status.Error -> Unit
                    is Status.Success -> {
                        variableList.value = status.data
                    }
                }
            }
        }
    }
}