package br.com.drmsolucoes.sunrisealarmclock.service

import br.com.drmsolucoes.sunrisealarmclock.domain.model.BaseData
import br.com.drmsolucoes.sunrisealarmclock.service.model.SunriseSunsetModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rafaelneiva on 21/10/18.
 */
interface SunriseSunsetAPI {

    @GET("json")
    fun fetch(@Query("lat") lat: String,
              @Query("lng") lng: String,
              @Query("date") date: String): Call<BaseData<SunriseSunsetModel>>
}
