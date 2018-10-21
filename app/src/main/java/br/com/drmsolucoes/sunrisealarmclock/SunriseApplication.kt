package br.com.drmsolucoes.sunrisealarmclock

import android.app.Activity
import android.app.Application
import br.com.drmsolucoes.sunrisealarmclock.di.app.DaggerAppComponent
import br.com.drmsolucoes.sunrisealarmclock.service.APIClient
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by rafaelneiva on 12/06/18.
 */
class SunriseApplication : Application(), HasActivityInjector {

    companion object {
        lateinit var instance: SunriseApplication
    }

    lateinit var apiClient: APIClient

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingActivityInjector
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initInjector()
        initApiClient()
    }

    private fun initApiClient() {
        apiClient = APIClient("https://api.sunrise-sunset.org/")
    }

    private fun initInjector() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}