package br.com.drmsolucoes.sunrisealarmclock.viewmodel

import javax.inject.Inject

/**
 * Created by rafaelneiva on 12/06/18.
 */
class MainViewModel @Inject constructor() : BaseViewModel<MainViewInteractor>() {

    fun onClick() {
        navigator?.showToast()
    }
}