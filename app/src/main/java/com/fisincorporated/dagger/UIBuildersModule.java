package com.fisincorporated.dagger;


import com.fisincorporated.mvp.MvpActivity;
import com.fisincorporated.mvprxbus.MvpRxBusActivity;
import com.fisincorporated.mvvm.MvvmActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UIBuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {MvpActivityModule.class})
    abstract MvpActivity bindMvpActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {MvpOsActivityModule.class})
    abstract MvpRxBusActivity bindMvpOSActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {})
    abstract MvvmActivity bindMvvmActivity();

    // Add more bindings here for other sub components
    // Be sure not to provide any dependencies for the subcomponent here since this module will be included in the application component and could thereby have application scope.

}
