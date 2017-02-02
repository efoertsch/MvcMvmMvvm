package com.fisincorporated.common;

import java.util.List;

public interface IStationModel {
    List<IStationControl> getStationControls();

    String getStationName();

    String getBigButtonName();

    String getLogHint();

    void setLogText(String logText);

    void setStationSwitchValue(int position, boolean onOff);
}
