package com.sdk.data.manager

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sdk.data.network.main.MainService
import com.sdk.domain.model.appeal.Murojaatlar

class FilterPagingSource(private val apiService: MainService, private val mapQuery: HashMap<String, Any>) :
    PagingSource<Int, Murojaatlar>() {
    override fun getRefreshKey(state: PagingState<Int, Murojaatlar>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Murojaatlar> {
        return try {
            mapQuery["page"] = params.key ?: 1
            val response = apiService.searchAppeal(mapQuery).body()?.data
            var nextPageNumber: Int? = null

            if (response?.pagination?.next_page != null) {
                val uri = Uri.parse(response.pagination.next_page.toString())
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(data = response?.result!!, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            println("@@@$e")
            LoadResult.Error(e)
        }
    }
}