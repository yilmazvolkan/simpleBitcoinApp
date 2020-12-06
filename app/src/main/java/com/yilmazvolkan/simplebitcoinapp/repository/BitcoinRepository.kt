package com.yilmazvolkan.simplebitcoinapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yilmazvolkan.simplebitcoinapp.BitcoinApplication
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApi
import com.yilmazvolkan.simplebitcoinapp.data.database.toDataEntityList
import com.yilmazvolkan.simplebitcoinapp.data.database.toDataList
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class BitcoinRepository {

    @Inject
    lateinit var bitcoinApiService: BitcoinApi

    private val _bitcoinData by lazy { MutableLiveData<List<BitcoinData>>() }
    val bitcoinData: LiveData<List<BitcoinData>>
        get() = _bitcoinData

    private val _isInProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isInProgress

    private val _isError by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean>
        get() = _isError


    init {
        DaggerAppComponent.create().inject(this)
    }

    private fun insertData(): Disposable {
        return bitcoinApiService.getBitcoinValues("1year")
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<BitcoinResult> {
        return object : DisposableSubscriber<BitcoinResult>() {

            override fun onNext(bitcoinResult: BitcoinResult?) {
                if (bitcoinResult != null) {
                    val entityList = bitcoinResult.values.toList().toDataEntityList()
                    BitcoinApplication.database.apply {
                        dataDao().insertData(entityList)
                    }
                }
            }

            override fun onError(t: Throwable?) {
                _isInProgress.postValue(true)
                Log.e("insertData()", "BitcoinResult error: ${t?.message}")
                _isError.postValue(true)
                _isInProgress.postValue(false)
            }

            override fun onComplete() {
                Log.v("insertData()", "insert success")
                getBitcoinQuery()
            }
        }
    }

    private fun getBitcoinQuery(): Disposable {
        return BitcoinApplication.database.dataDao()
            .queryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataEntityList ->
                    _isInProgress.postValue(true)
                    if (dataEntityList != null && dataEntityList.isNotEmpty()) {
                        _isError.postValue(false)
                        _bitcoinData.postValue(dataEntityList.toDataList())
                    } else {
                        insertData()
                    }
                    _isInProgress.postValue(false)

                },
                {
                    _isInProgress.postValue(true)
                    Log.e("getBitcoinQuery()", "Database error: ${it.message}")
                    _isError.postValue(true)
                    _isInProgress.postValue(false)
                }
            )
    }

    fun fetchDataFromDatabase(): Disposable = getBitcoinQuery()
}