package br.com.drmsolucoes.sunrisealarmclock.domain.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import br.com.drmsolucoes.sunrisealarmclock.domain.model.BaseData
import br.com.drmsolucoes.sunrisealarmclock.domain.model.MainModel
import br.com.drmsolucoes.sunrisealarmclock.service.APIClient
import br.com.drmsolucoes.sunrisealarmclock.service.BaseCallback
import br.com.drmsolucoes.sunrisealarmclock.service.SunriseSunsetAPI
import br.com.drmsolucoes.sunrisealarmclock.service.model.SunriseSunsetModel
import javax.inject.Inject

/**
 * Created by rafaelneiva on 21/10/18.
 */
class SunriseSunsetRepository @Inject constructor(apiClient: APIClient) {

    val sunriseSunsetAPI = apiClient.retrofit.create(SunriseSunsetAPI::class.java)

    fun fetch(lat: String, lng: String, date: String): LiveData<BaseData<SunriseSunsetModel>> {
        val data = MutableLiveData<BaseData<SunriseSunsetModel>>()

        sunriseSunsetAPI.fetch(lat, lng, date).enqueue(object: BaseCallback<BaseData<SunriseSunsetModel>>() {
            override fun onSuccess(response: BaseData<SunriseSunsetModel>?) {
                data.value = response
            }

            override fun onError(error: BaseData<BaseData<SunriseSunsetModel>>) {
                Log.e("REPO_CALL", error.message)
            }
        })

        return data
    }

}