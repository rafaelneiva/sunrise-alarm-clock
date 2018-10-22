package br.com.drmsolucoes.sunrisealarmclock.viewmodel

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import br.com.drmsolucoes.sunrisealarmclock.domain.model.BaseData
import br.com.drmsolucoes.sunrisealarmclock.domain.model.MainModel
import br.com.drmsolucoes.sunrisealarmclock.domain.repository.SunriseSunsetRepository
import javax.inject.Inject

/**
 * Created by rafaelneiva on 12/06/18.
 */
class MainViewModel @Inject constructor(val repository: SunriseSunsetRepository) : BaseViewModel<MainViewInteractor>() {

    fun fetch(lat: String, lng: String, date: String): LiveData<BaseData<MainModel>> {
        return Transformations.map(repository.fetch(lat, lng, date)) { input ->
            val ret: BaseData<MainModel> = BaseData()
            var mainModel = MainModel()

            ret
        }
    }

    fun onClick() {
        navigator?.showToast()
    }
}