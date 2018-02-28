package com.fisincorporated.dagger;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class AndroidArchitectureApplication extends DaggerApplication {

    private static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerApplicationComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}
