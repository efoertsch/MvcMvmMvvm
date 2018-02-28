package com.fisincorporated.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StationModel implements IStationModel {

    private static StationModel stationModel;
    private static final String NO_LOG_ENTRIES = "No log entries";

    ArrayList<IStationControl> stationControls;

    private String logText = NO_LOG_ENTRIES;

    private StationModel(){}

    public static IStationModel getStationModel(){
        if (stationModel == null){
            stationModel = new StationModel();
            stationModel.createStationControls();
        }
        return stationModel;
    }

    private void createStationControls() {
        StationControl stationControl;
        stationControls = new ArrayList<>();

        stationControl = new StationControl("Docking Clamps Released ", false);
        stationControls.add(stationControl);

        stationControl = new StationControl("Inertial Dampers Engaged", true);
        stationControls.add(stationControl);

        stationControl = new StationControl("Warp Containment Field Engaged", false);
        stationControls.add(stationControl);

        stationControl = new StationControl("Weapon Systems Online", false);
        stationControls.add(stationControl);
    }

    @Override
    public List<IStationControl> getStationControls(){
        return stationControls;
    }

    @Override
    public String getStationName(){
        return "Engineering";
    }

    @Override
    public String getBigButtonName() {
        return "Save Log";
    }

    @Override
    public void setLogText(String logText) {
        this.logText = ((new Date()).toString() + " "  + logText + "\n" + (this.logText.equals(NO_LOG_ENTRIES) ? "" : this.logText));
    }

    @Override
    public String getLogText() {
        return logText;
    }

    @Override
    public void setStationSwitchValue(int position, boolean onOff){
        if (position <= stationControls.size()){
            stationControls.get(position).setOnOff(onOff);
        }
    }


}
