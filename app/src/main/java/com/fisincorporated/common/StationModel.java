package com.fisincorporated.common;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fisincorporated.mvc_mvp_mvvm.BR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Note superclass of BaseObservable, @Bindable annotation, and notifyPropertyChanged only come into
// play on DataBinding example
public class StationModel extends BaseObservable implements IStationModel {

    private static StationModel stationModel;
    private static final String NO_LOG_ENTRIES = "No log entries";
    private ArrayList<IStationControl> stationControls;
    private String logText = NO_LOG_ENTRIES;

    private StationModel(){}

    // This is used for non-databinding example
    public static IStationModel getIStationModel(){
        if (stationModel == null){
            stationModel = new StationModel();
            stationModel.createStationControls();
        }
        return stationModel;
    }

    // This version is used for data binding example
    public static StationModel getStationModel(){
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

        stationControl = new StationControl("Inertial Dampers Engaged", false);
        stationControls.add(stationControl);

        stationControl = new StationControl("Warp Containment Field Engaged", false);
        stationControls.add(stationControl);

        stationControl = new StationControl("Weapon Systems Online", false);
        stationControls.add(stationControl);
    }

    @Bindable
    @Override
    public List<IStationControl> getStationControls(){
        return stationControls;
    }

    @Bindable
    @Override
    public String getStationName(){
        return "Engineering";
    }

    @Bindable
    @Override
    public String getLogButtonText() {
        return "Save Log";
    }

    @Override
    public void setLogText(String logText) {
        this.logText = ((new Date()).toString() + " "  + logText + "\n" + (this.logText.equals(NO_LOG_ENTRIES) ? "" : this.logText));
        notifyPropertyChanged(BR.logText);
    }

    @Bindable
    @Override
    public String getLogText() {
        return logText;
    }

    @Override
    public void setStationSwitchValue(int position, boolean onOff) {
        if (position <= stationControls.size()){
            stationControls.get(position).setOnOff(onOff);
            setLogText(stationControls.get(position).getStationControlName() + " set " + (onOff ? "On" : "Off"));
        } else {
            throw new IndexOutOfBoundsException("There are  " + stationControls.size() + " switches indexed 0 -> " + stationControls.size() + " but you asked to change position:" + position);
        }
        notifyPropertyChanged(BR.logText);
    }


}
