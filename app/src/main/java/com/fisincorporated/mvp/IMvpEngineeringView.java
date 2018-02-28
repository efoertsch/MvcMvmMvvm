package com.fisincorporated.mvp;

import android.view.View;

import com.fisincorporated.common.IStationControl;

import java.util.List;

public interface IMvpEngineeringView {

    MvpEngineeringView assignView(View view) ;

    MvpEngineeringView assignPresenter(IMvpEngineeringPresenter iMvpEngineeringPresenter);

    void setStationName(String stationName);

    void setBigButtonName(String bigButtonName);

    void setStationLogEntries(String logEntries);

    void setStationEngineeringControls(List<IStationControl> stationControls);

}
