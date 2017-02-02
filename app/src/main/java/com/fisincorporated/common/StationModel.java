package com.fisincorporated.common;

import java.util.ArrayList;
import java.util.List;

public class StationModel implements IStationModel {

    private static StationModel stationModel;

    ArrayList<IStationControl> stationControls;

    private String logText;

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
    public String getLogHint(){
        return "Engineering log";
    }

    @Override
    public void setLogText(String logText) {
        this.logText = logText;
    }

    @Override
    public void setStationSwitchValue(int position, boolean onOff){
        if (position <= stationControls.size()){
            stationControls.get(position).setOnOff(onOff);
        }
    }
}
