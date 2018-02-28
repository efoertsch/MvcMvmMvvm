package com.fisincorporated.dagger;


import com.fisincorporated.common.IStationModel;
import com.fisincorporated.mvp.IMvpEngineeringPresenter;
import com.fisincorporated.mvp.IMvpEngineeringView;
import com.fisincorporated.mvp.MvpEngineeringPresenter;
import com.fisincorporated.mvp.MvpEngineeringView;

import dagger.Module;
import dagger.Provides;

@Module
abstract class MvpActivityModule {

    @ActivityScope
    @Provides
    static IMvpEngineeringPresenter getIMvpEngineerPresenter(IStationModel iStationModel) {
        return new MvpEngineeringPresenter(iStationModel);
    }

    @ActivityScope
    @Provides
    static IMvpEngineeringView getIMvpEngineerView() {
        return new MvpEngineeringView();
    }

}
