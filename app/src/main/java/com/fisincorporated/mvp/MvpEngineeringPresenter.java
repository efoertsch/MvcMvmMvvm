package com.fisincorporated.mvp;

import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;

import javax.inject.Inject;


public class MvpEngineeringPresenter implements IMvpEngineeringPresenter, SwitchChangeListener {

    private static final String TAG = MvpEngineeringPresenter.class.getSimpleName();

    private IMvpEngineeringView iMvpEngineeringView;

    private IStationModel iStationModel;

    @Inject
    public MvpEngineeringPresenter(IStationModel iStationModel) {
        this.iStationModel = iStationModel;
    }

    public IMvpEngineeringPresenter assignEngineeringView(IMvpEngineeringView iMvpEngineeringView) {
        this.iMvpEngineeringView = iMvpEngineeringView;
        return this;
    }

    @Override
    public void onLoad() {
        setFieldValues();
    }

    @Override
    public void saveStationLogEntry(String stationLog) {
        Log.d(TAG, "Saving station log:" + stationLog);
        iStationModel.setLogText(stationLog);
        displayCurrentLogEntries();
    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
        displayCurrentLogEntries();
    }

    private void setFieldValues() {
        iMvpEngineeringView.setStationName(iStationModel.getStationName());
        iMvpEngineeringView.setBigButtonName(iStationModel.getLogButtonText());
        displayCurrentLogEntries();
        iMvpEngineeringView.setStationEngineeringControls(iStationModel.getStationControls());
    }

    private void displayCurrentLogEntries() {
        iMvpEngineeringView.setStationLogEntries(iStationModel.getLogText());
    }

}
