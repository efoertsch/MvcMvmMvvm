package com.fisincorporated.dagger;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.mvvm.MvvmViewModel;

import dagger.Module;
import dagger.Provides;


@Module
abstract class MvvmActivityModule {

    @ActivityScope
    @Provides
    static MvvmViewModel getMvvmViewModel(IStationModel iStationModel) {
        return new MvvmViewModel(iStationModel);
    }

}

