package com.raizlabs.mvc_mvp_mvvm.mvp;

import com.raizlabs.mvc_mvp_mvvm.common.IStationControl;

import java.util.List;

public interface IMvpEngineeringView {

    void setStationName(String stationName);

    void setBigButtonName(String bigButtonName);

    void setLogHint(String logHint);

    void setStationEngineeringControls(List<IStationControl> stationControls);

}
