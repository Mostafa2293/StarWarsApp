package com.mostafa.starwarsfans.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mostafa.starwarsfans.data.people.model.People

abstract class BasePagingDataSource<Model : Any> {
    open val startingPageIndex = 1
    open val pageSize = 10
    open var canGoNext: Boolean = false
      var searchKey:String = ""
    fun createPagingSource(): PagingSource<Int, Model> {
        return object : PagingSource<Int, Model>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Model> {
                val position = params.key ?: startingPageIndex
                return try {
                    val response = loadData(position, pageSize)
                    Log.d("RESPONSE", "--> $response")

                    LoadResult.Page(
                            data = response,
                            prevKey = if (position == startingPageIndex) null else position - 1,
//                            nextKey = if (!canGoNext) null else position + 1
                          nextKey = if (response.isEmpty()) null else position + 1

                    )
                } catch (exception: Throwable) {
                    exception.printStackTrace()
                    LoadResult.Error(exception)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Model>): Int? {
                return state.anchorPosition?.let { anchorPosition ->
                    val anchorPage = state.closestPageToPosition(anchorPosition)
                    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                }
            }
        }
    }


    fun createPagingSourceWithKey(searchKey:String): PagingSource<Int, Model> {
        this.searchKey = searchKey
        return createPagingSource()
    }


    abstract suspend fun loadData(page: Int, size: Int): List<Model>

}
