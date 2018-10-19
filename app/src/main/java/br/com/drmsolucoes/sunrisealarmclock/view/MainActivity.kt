package br.com.drmsolucoes.sunrisealarmclock.view

import android.widget.Toast
import br.com.drmsolucoes.sunrisealarmclock.R
import br.com.drmsolucoes.sunrisealarmclock.databinding.ActivityMainBinding
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainNavigator
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainViewModel

/**
 * Created by rafaelneiva on 12/06/18.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
        getBinding().viewModel = getViewModel()
        getViewModel().navigator = this
    }

    override fun showToast() {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }

}
