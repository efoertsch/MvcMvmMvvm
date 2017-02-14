package com.fisincorporated.mvp;

import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;

import javax.inject.Inject;


public class MvpEngineeringPresenter implements IMvpEngineeringPresenter, SwitchChangeListener {

    private static final String TAG = MvpEngineeringPresenter.class.getSimpleName();

    private IMvpEngineeringView iMvpEngineeringView;


    @Inject
    public IStationModel iStationModel;

    @Inject
    public MvpEngineeringPresenter() { }

    // This version is used for testing. For some reason Dagger was not injecting
    // IStationModel for test.
    public MvpEngineeringPresenter(IStationModel iStationModel) {
        this.iStationModel = iStationModel;
    }


    public MvpEngineeringPresenter assignEngineeringView(IMvpEngineeringView iMvpEngineeringView) {
        this.iMvpEngineeringView = iMvpEngineeringView;
        return this;
    }

    @Override
    public void onLoad() {
        setFieldValues(iStationModel);
    }

    @Override
    public void saveStationLog(String stationLog) {
        Log.d(TAG, "Saving station log:" + stationLog);
    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
       // Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

    private void setFieldValues(IStationModel stationModel) {
        iMvpEngineeringView.setStationName(stationModel.getStationName());
        iMvpEngineeringView.setBigButtonName(stationModel.getBigButtonName());
        iMvpEngineeringView.setLogHint(stationModel.getLogHint());
        iMvpEngineeringView.setStationEngineeringControls(stationModel.getStationControls());
    }

}
