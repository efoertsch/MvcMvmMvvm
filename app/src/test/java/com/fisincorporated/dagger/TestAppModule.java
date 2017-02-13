package com.fisincorporated.dagger;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationControl;
import com.fisincorporated.mvp.MvpEngineeringPresenter;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.when;

@Module
public class TestAppModule  {

    @Provides
    public IStationModel getStationModel() {

        IStationModel iStationModel = Mockito.mock(IStationModel.class);
        when(iStationModel.getStationName()).thenReturn("Mocked Station");
        when(iStationModel.getStationControls().size()).thenReturn(2);
        when(iStationModel.getBigButtonName()).thenReturn(("Log Button"));
        when(iStationModel.getLogHint()).thenReturn("Enter log text here");

        for (int i = 0; i < 2; ++i) {
            when(iStationModel.getStationControls().get(i)).thenReturn(new StationControl("Test Switch" + i,false));
        }
        return iStationModel;
    }

    @Provides
    public MvpEngineeringPresenter getMvpEngineeringPresenter() {
        return new MvpEngineeringPresenter();
    }

}

