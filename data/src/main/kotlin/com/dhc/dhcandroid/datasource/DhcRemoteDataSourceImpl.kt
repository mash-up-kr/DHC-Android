package com.dhc.dhcandroid.datasource

import com.dhc.dhcandroid.service.DhcService
import javax.inject.Inject

class DhcRemoteDataSourceImpl @Inject constructor(
    private val dhcService: DhcService,
): DhcRemoteDataSource {

}
