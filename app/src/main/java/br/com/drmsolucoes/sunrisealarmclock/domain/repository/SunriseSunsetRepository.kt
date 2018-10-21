package br.com.drmsolucoes.sunrisealarmclock.domain.repository

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by rafaelneiva on 21/10/18.
 */
interface SunriseSunsetRepository {

    @GET("json")
    fun fetch(): Call<*>
}
