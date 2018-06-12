package br.com.drmsolucoes.sunrisealarmclock.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean

/**
 * Created by rafaelneiva on 12/06/18.
 */
abstract class BaseViewModel<N> protected constructor() : ViewModel() {

    var showLoading = ObservableBoolean(false)

    var navigator: N? = null

}