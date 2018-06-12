package br.com.drmsolucoes.sunrisealarmclock

import android.app.Activity
import android.app.Application
import br.com.drmsolucoes.sunrisealarmclock.di.app.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by rafaelneiva on 12/06/18.
 */
class SunriseApplication : Application(), HasActivityInjector {


    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingActivityInjector
    }

    override fun onCreate() {
        super.onCreate()

        initInjector()
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}