package br.com.drmsolucoes.sunrisealarmclock.view

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by rafaelneiva on 12/06/18.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), HasSupportFragmentInjector {

    protected lateinit var mViewModel: V
    protected lateinit var mDataBinding: T

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    @Inject
    lateinit var mViewModelProvider: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<V>

    protected abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId())

        mViewModel = ViewModelProviders.of(this, this.mViewModelProvider).get(getViewModelClass())

        init()
    }

    fun getViewModel(): V = mViewModel

    fun getBinding(): T = mDataBinding


}
