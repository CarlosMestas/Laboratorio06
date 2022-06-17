package com.aangles_cmestas_mquispeyn.laboratorio06

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.firebase.firestore.Query
import javax.inject.Inject


import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.CARPARKS_COLLECTION
import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.PAGE_SIZE
import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.NAME_PROPERTY
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ActivityViewModel@Inject constructor(
    private val queryProductsByName: Query
) : ViewModel() {
    val flow = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE
        )
    ) {
        CarParkPagingSource(queryProductsByName)
    }.flow.cachedIn(viewModelScope)
}