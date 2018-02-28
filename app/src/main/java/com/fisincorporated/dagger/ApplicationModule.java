package com.fisincorporated.dagger;


import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.jakewharton.rxrelay2.PublishRelay;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    // Using an interface to the model so defining injection here
    @Provides
    @Singleton
    public IStationModel getStationModel(){
        return StationModel.getStationModel();
    }

    // RxJava Bus to replace callback interfaces
    @Provides
    @Singleton
    PublishRelay<Object> providePublishRelay() {
        return PublishRelay.create();
    }


}
