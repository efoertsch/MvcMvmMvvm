package com.raizlabs.mvc_mvp_mvvm.mvp;

import com.raizlabs.mvc_mvp_mvvm.common.SwitchChangeListener;

public interface IMvpEngineeringPresenter extends SwitchChangeListener {
    /**
     * onLoad called by view when view ready to display the data
     */
    void onLoad();

    void saveStationLog(String stationLog);


}
