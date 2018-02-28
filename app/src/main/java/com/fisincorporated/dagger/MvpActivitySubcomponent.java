package com.fisincorporated.dagger;




import com.fisincorporated.mvp.MvpActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {MvpActivityModule.class})
public interface MvpActivitySubcomponent extends AndroidInjector<MvpActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MvpActivity> {
    }
}

