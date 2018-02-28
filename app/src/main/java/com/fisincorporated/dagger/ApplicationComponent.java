package com.fisincorporated.dagger;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, UIBuildersModule.class,})
public interface ApplicationComponent extends AndroidInjector<AndroidArchitectureApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(AndroidArchitectureApplication application);

        ApplicationComponent build();
    }

    // The injection in strongly typed so you must be specific in the (in this case)
    // activity that needs to have the dependencies injected.
    void inject(AndroidArchitectureApplication application);

}

