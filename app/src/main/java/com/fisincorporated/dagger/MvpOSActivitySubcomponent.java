package com.fisincorporated.dagger;

import com.fisincorporated.mvprxbus.MvpRxBusActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;



@ActivityScope
@Subcomponent(modules = {MvpOsActivityModule.class})
public interface MvpOSActivitySubcomponent extends AndroidInjector<MvpRxBusActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MvpRxBusActivity> {
    }
}

