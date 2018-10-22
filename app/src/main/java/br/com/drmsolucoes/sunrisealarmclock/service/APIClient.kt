package br.com.drmsolucoes.sunrisealarmclock.service

import br.com.drmsolucoes.sunrisealarmclock.SunriseApplication
import br.com.drmsolucoes.sunrisealarmclock.util.Utils
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rafaelneiva on 22/10/18.
 */

class APIClient(baseUrl: String) {

    val retrofit: Retrofit
    val okHttpClient: OkHttpClient

    companion object {
        @get:Synchronized
        lateinit var instance: APIClient
    }

    private val client: OkHttpClient
        get() = OkHttpClient()
                .newBuilder()
                //                .addInterceptor(requestIntercept)
                .addInterceptor(checkConnectionInterceptor)
                .addInterceptor(loggingCapableHttpClient)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .build()

    private val checkConnectionInterceptor = Interceptor { chain ->
        if (!Utils.isOnline(SunriseApplication.instance)) {
            throw NoConnectionException()
        }
        chain.proceed(chain.request())
    }

    private val loggingCapableHttpClient: HttpLoggingInterceptor
        get() {
            val mLogging = HttpLoggingInterceptor()
            mLogging.level = HttpLoggingInterceptor.Level.BODY

            return mLogging
        }

    private val requestIntercept = Interceptor { chain ->

        val original = chain.request()
        val requestBuilder = original.newBuilder()
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        response

    }

    init {
        instance = this
        okHttpClient = client

        val builder = GsonBuilder()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build()
    }

    fun cancelAllRequests() {
        instance.okHttpClient.dispatcher().cancelAll()
    }
}


