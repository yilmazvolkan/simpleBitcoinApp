package com.yilmazvolkan.simplebitcoinapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinApi
import com.yilmazvolkan.simplebitcoinapp.data.api.BitcoinResult
import com.yilmazvolkan.simplebitcoinapp.data.database.DataDao
import com.yilmazvolkan.simplebitcoinapp.data.database.toDataEntityList
import com.yilmazvolkan.simplebitcoinapp.data.database.toDataList
import com.yilmazvolkan.simplebitcoinapp.di.DaggerAppComponent
import com.yilmazvolkan.simplebitcoinapp.models.BitcoinData
import com.yilmazvolkan.simplebitcoinapp.models.DateModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class BitcoinRepository {

    @Inject
    lateinit var bitcoinApiService: BitcoinApi

    @Inject
    lateinit var bitcoinDao: DataDao

    @Inject
    lateinit var dateModel: DateModel

    val bitcoinData by lazy { MutableLiveData<List<BitcoinData>>() }
    val isInProgress by lazy { MutableLiveData<Boolean>() }
    val isError by lazy { MutableLiveData<Boolean>() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    private fun insertData(): Disposable {
        return bitcoinApiService.getBitcoinValues(FETCH_TIME)
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<BitcoinResult> {
        return object : DisposableSubscriber<BitcoinResult>() {

            override fun onNext(bitcoinResult: BitcoinResult?) {
                if (bitcoinResult != null) {
                    val entityList = bitcoinResult.values.toList().toDataEntityList()
                    bitcoinDao.insertData(entityList)
                }
            }

            override fun onError(t: Throwable?) {
                isInProgress.postValue(true)
                Log.e("insertData()", "BitcoinResult error: ${t?.message}")
                isError.postValue(true)
                isInProgress.postValue(false)
            }

            override fun onComplete() {
                Log.v("insertData()", "insert success")
                dateModel.updateTodayDate()
                getBitcoinQuery()
            }
        }
    }

    private fun getBitcoinQuery(): Disposable {
        return bitcoinDao
            .queryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataEntityList ->
                    isInProgress.postValue(true)
                    if (dataEntityList != null && dataEntityList.isNotEmpty() && dateModel.checkDates()) {
                        isError.postValue(false)
                        bitcoinData.postValue(dataEntityList.toDataList())
                        Log.d("TEST", "Loaded from database")
                    } else {
                        clearAll()
                    }
                    isInProgress.postValue(false)

                },
                {
                    isInProgress.postValue(true)
                    Log.e("getBitcoinQuery()", "Database error: ${it.message}")
                    isError.postValue(true)
                    isInProgress.postValue(false)
                }
            )
    }

    private fun clearAll(): Disposable {
        return clearTable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertData()
                Log.d("TEST", "Loaded from remote")
            }, {/*error*/ })
    }

    private fun clearTable(): Observable<Boolean> {
        return Observable.create { emitter ->
            try {
                bitcoinDao.clearTable()
            } catch (e: Exception) {
                emitter.onError(e)
            }
            emitter.onNext(true)
            emitter.onComplete()
        }
    }

    fun fetchDataFromDatabase(): Disposable = getBitcoinQuery()

    companion object{
        private const val FETCH_TIME = "1year"
    }
}