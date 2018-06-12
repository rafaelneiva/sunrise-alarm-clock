package br.com.drmsolucoes.sunrisealarmclock.view

import android.widget.Toast
import br.com.drmsolucoes.sunrisealarmclock.R
import br.com.drmsolucoes.sunrisealarmclock.databinding.ActivityMainBinding
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainNavigator
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainViewModel
import kotlin.reflect.KClass

/**
 * Created by rafaelneiva on 12/06/18.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    lateinit var bind: ActivityMainBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun initView() {
        bind = getBinding()
        getViewModel().navigator = this
    }

    override fun showToast() {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }

}
