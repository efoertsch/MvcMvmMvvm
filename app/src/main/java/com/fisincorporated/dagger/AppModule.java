package com.fisincorporated.dagger;


import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    // Using an interface to the model so defining injection here
    @Provides
    @Singleton
    public IStationModel getStationModel(){
        return StationModel.getStationModel();
    }

}
