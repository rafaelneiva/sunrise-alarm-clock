package br.com.drmsolucoes.sunrisealarmclock.di.builders

import android.arch.lifecycle.ViewModelProvider
import br.com.drmsolucoes.sunrisealarmclock.di.ViewModelKey
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.MainViewModel
import br.com.drmsolucoes.sunrisealarmclock.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by rafaelneiva on 12/06/18.
 */

@Module
abstract class ViewModelBuilder {

    // Main
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel)


    // ViewModel Factory
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}
