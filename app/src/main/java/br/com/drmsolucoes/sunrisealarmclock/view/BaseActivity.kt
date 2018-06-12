package br.com.drmsolucoes.sunrisealarmclock.view

import android.app.Fragment
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by rafaelneiva on 12/06/18.
 */
abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity(), HasFragmentInjector {

    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    @Inject
    lateinit var mViewModelProvider: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): KClass<V>?

    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())

        if (getViewModelClass() != null)
            mViewModel = getViewModelClass()?.java?.let { ViewModelProviders.of(this, mViewModelProvider).get(it) }!!

        initView()
    }

    protected fun getBinding(): T {
        return mViewDataBinding
    }

    fun getViewModel(): V {
        return mViewModel
    }

}
