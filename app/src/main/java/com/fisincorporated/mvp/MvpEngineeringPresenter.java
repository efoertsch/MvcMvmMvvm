package com.fisincorporated.mvp;

import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;

public class MvpEngineeringPresenter implements IMvpEngineeringPresenter, SwitchChangeListener {

    private static final String TAG = MvpEngineeringPresenter.class.getSimpleName();

    private IMvpEngineeringView iMvpEngineeringView;
    private IStationModel iStationModel;

    public MvpEngineeringPresenter() {
        iStationModel = StationModel.getStationModel();
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
        Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

    private void setFieldValues(IStationModel stationModel) {
        iMvpEngineeringView.setStationName(stationModel.getStationName());
        iMvpEngineeringView.setBigButtonName(stationModel.getBigButtonName());
        iMvpEngineeringView.setLogHint(stationModel.getLogHint());
        iMvpEngineeringView.setStationEngineeringControls(stationModel.getStationControls());
    }

}