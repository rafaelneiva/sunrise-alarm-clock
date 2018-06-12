package br.com.drmsolucoes.sunrisealarmclock.di.builders

import br.com.drmsolucoes.sunrisealarmclock.di.Activity
import br.com.drmsolucoes.sunrisealarmclock.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by rafaelneiva on 12/06/18.
 */
@Module
abstract class ActivityBuilder {

    // Checkout
    @Activity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}