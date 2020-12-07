package com.yilmazvolkan.simplebitcoinapp.data.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface BitcoinApi {

    @GET("charts/market-price")
    fun getBitcoinValues(
        @Query("timespan") timespan: String
    ): Flowable<BitcoinResult>
}
