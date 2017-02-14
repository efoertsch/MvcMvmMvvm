package com.fisincorporated.dagger;

import android.util.Log;

import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.mvp.IMvpEngineeringView;
import com.fisincorporated.mvp.MvpEngineeringPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

public class TestMvpEngineeringPresenter {

    @Mock
    IMvpEngineeringView iMvpEngineeringView;

    @Inject
    MvpEngineeringPresenter mvpEngineeringPresenter;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {

        TestDiComponent component = DaggerTestDiComponent.builder()
                .testAppModule(new TestAppModule()).build();
        component.inject(this);
    }

    @Test
    public void testStationControlSwitchChange() {

        mvpEngineeringPresenter.assignEngineeringView(iMvpEngineeringView);
        mvpEngineeringPresenter.onLoad();

        mvpEngineeringPresenter.switchChanged(new SwitchChange(0, true));
        assertEquals(true, mvpEngineeringPresenter.iStationModel.getStationControls().get(0).isOnOff());
        mvpEngineeringPresenter.switchChanged(new SwitchChange(0, false));
        assertEquals(false, mvpEngineeringPresenter.iStationModel.getStationControls().get(0).isOnOff());

    }
}
