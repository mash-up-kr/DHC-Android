package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
import javax.inject.Inject

class DhcRemoteRepositoryImpl @Inject constructor(
    private val dhcRemoteDataSource: DhcRemoteDataSource,
): DhcRemoteRepository {

}
