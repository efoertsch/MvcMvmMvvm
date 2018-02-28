package com.fisincorporated.mvp;

import com.fisincorporated.common.SwitchChangeListener;

public interface IMvpEngineeringPresenter extends SwitchChangeListener {

    IMvpEngineeringPresenter assignEngineeringView(IMvpEngineeringView iMvpEngineeringView);
    /**
     * onLoad called by view when view ready to display the data
     */
    void onLoad();

    void saveStationLogEntry(String stationLogEntry);

}
