package br.com.drmsolucoes.sunrisealarmclock.di.app

import android.app.Application
import br.com.drmsolucoes.sunrisealarmclock.SunriseApplication
import br.com.drmsolucoes.sunrisealarmclock.di.builders.ActivityBuilder
import br.com.drmsolucoes.sunrisealarmclock.di.builders.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rafaelneiva on 12/06/18.
 */
@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelBuilder::class,
    ActivityBuilder::class
])
interface AppComponent {

    fun inject(application: SunriseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}