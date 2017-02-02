package com.fisincorporated.mvp;

import com.fisincorporated.common.SwitchChangeListener;

public interface IMvpEngineeringPresenter extends SwitchChangeListener {
    /**
     * onLoad called by view when view ready to display the data
     */
    void onLoad();

    void saveStationLog(String stationLog);


}
