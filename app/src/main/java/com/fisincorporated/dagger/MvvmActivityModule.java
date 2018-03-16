package com.fisincorporated.dagger;

import com.fisincorporated.mvvm.MvvmViewModel;

import dagger.Module;
import dagger.Provides;


@Module
abstract class MvvmActivityModule {

    @ActivityScope
    @Provides
    static MvvmViewModel getMvvmViewModel() {
        return new MvvmViewModel();
    }

}

