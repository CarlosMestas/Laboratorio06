package com.aangles_cmestas_mquispeyn.laboratorio06.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.CARPARKS_COLLECTION
import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.PAGE_SIZE
import com.aangles_cmestas_mquispeyn.laboratorio06.utils.Constants.NAME_PROPERTY
import com.google.firebase.firestore.Query
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideQueryProductsByName() = FirebaseFirestore.getInstance()
        .collection(CARPARKS_COLLECTION)
        .orderBy(NAME_PROPERTY, Query.Direction.ASCENDING)
        .limit(PAGE_SIZE.toLong())
}

/*
provideQueryProductsByName() = FirebaseFirestore.getInstance()
            .collection(PRODUCTS_COLLECTION)
            .orderBy(NAME_PROPERTY, ASCENDING)
            .limit(PAGE_SIZE.toLong())
*/