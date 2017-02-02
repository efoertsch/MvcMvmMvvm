package com.raizlabs.mvc_mvp_mvvm.common;

public class StationControl implements IStationControl {
    private final String stationControlName;
    private boolean onOff;

    @Override
    public String getStationControlName() {
        return stationControlName;
    }

    @Override
    public boolean isOnOff() {
        return onOff;
    }

    @Override
    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public StationControl(String text, boolean onOff){
        this.stationControlName = text;
        this.onOff = onOff;
    }

}
