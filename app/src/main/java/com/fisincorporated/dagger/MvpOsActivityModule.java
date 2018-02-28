package com.fisincorporated.dagger;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.mvprxbus.IMvpRxBusEngineeringPresenter;
import com.fisincorporated.mvprxbus.IMvpRxBusEngineeringView;
import com.fisincorporated.mvprxbus.MvpRxBusEngineeringPresenter;
import com.fisincorporated.mvprxbus.MvpRxBusEngineeringView;
import com.jakewharton.rxrelay2.PublishRelay;

import dagger.Module;
import dagger.Provides;

@Module
abstract class MvpOsActivityModule {

    @ActivityScope
    @Provides
    static IMvpRxBusEngineeringPresenter getIMvpOSEngineerPresenter(IStationModel iStationModel, PublishRelay<Object> publishRelay) {
        return new MvpRxBusEngineeringPresenter(iStationModel, publishRelay);
    }

    @ActivityScope
    @Provides
    static IMvpRxBusEngineeringView getIMvpOSEngineerView(PublishRelay<Object> publishRelay) {
        return new MvpRxBusEngineeringView(publishRelay);
    }

}