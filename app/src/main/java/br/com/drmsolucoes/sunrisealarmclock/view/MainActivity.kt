package br.com.drmsolucoes.sunrisealarmclock.view

import android.arch.lifecycle.Observer
import android.widget.Toast
import br.com.drmsolucoes.sunrisealarmclock.R
import br.com.drmsolucoes.sunrisealarmclock.databinding.ActivityMainBinding
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainViewInteractor
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainViewModel

/**
 * Created by rafaelneiva on 12/06/18.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainViewInteractor {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
        getBinding().viewModel = getViewModel()
        getViewModel().navigator = this
    }

    override fun showToast() {
        getViewModel().fetch("-18.9083814", "-48.2613538", "2018-06-12").observe(this, Observer {

        })
    }

}
