package com.aangles_cmestas_mquispeyn.laboratorio06

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aangles_cmestas_mquispeyn.laboratorio06.classes.CarPark
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class CarParkPagingSource(
    private val queryProductsByName: Query
) : PagingSource<QuerySnapshot, CarPark>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, CarPark>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CarPark> {
        return try {
            val currentPage = params.key ?: queryProductsByName.get().await()
            val lastVisibleProduct = currentPage.documents[currentPage.size() - 1]
            val nextPage = queryProductsByName.startAfter(lastVisibleProduct).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(CarPark::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}