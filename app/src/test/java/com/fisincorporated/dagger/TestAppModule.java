package com.fisincorporated.dagger;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.mvp.MvpEngineeringPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestAppModule {

    @Provides
    @Singleton
    public IStationModel getStationModel() {

        IStationModel iStationModel =  StationModel.getStationModel();
        return iStationModel;
    }

    @Provides
    public MvpEngineeringPresenter getMvpEngineeringPresenter(IStationModel istationModel) {
        return new MvpEngineeringPresenter(istationModel);
    }

}

