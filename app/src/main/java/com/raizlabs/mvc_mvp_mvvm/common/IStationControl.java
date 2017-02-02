package com.raizlabs.mvc_mvp_mvvm.common;

/**
 * Created by ericfoertsch on 2/2/17.
 */
public interface IStationControl {
    String getStationControlName();

    boolean isOnOff();

    void setOnOff(boolean onOff);
}
