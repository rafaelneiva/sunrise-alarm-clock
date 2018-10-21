package br.com.drmsolucoes.sunrisealarmclock.service

import br.com.drmsolucoes.sunrisealarmclock.domain.model.BaseData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rafaelneiva on 03/01/18.
 */

abstract class BaseCallback<T : Any> : Callback<T> {

    abstract fun onSuccess(response: T?)

    abstract fun onError(error: BaseData)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            try {
                onError(Gson().fromJson(response.errorBody()!!.string(), BaseData::class.java))
            } catch (e: Exception) {
                e.printStackTrace()
                val errorResponse = BaseData()
                errorResponse.message = e.message!!
                onError(errorResponse)
            }

        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val response = BaseData()
        response.message = t.message!!
        onError(response)
    }
}
