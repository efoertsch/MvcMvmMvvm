package com.fisincorporated.dagger;


import com.fisincorporated.mvp.MvpActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface DiComponent {

    // The injection in strongly typed so you must be specific in the (in this case)
    // activity that needs to have the dependencies injected.
    void inject(MvpActivity activity);

    // void inject(MyFragment fragment);
    // void inject(MyService service);
}

