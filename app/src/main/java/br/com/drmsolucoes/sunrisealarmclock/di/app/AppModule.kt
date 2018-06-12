package br.com.drmsolucoes.sunrisealarmclock.di.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rafaelneiva on 12/06/18.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }
}
