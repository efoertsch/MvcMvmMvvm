package com.fisincorporated.mvvm;

import android.widget.TextView;

import com.fisincorporated.common.IStationModel;

import javax.inject.Inject;

/**
 * Needed this as I couldn't do both update to stationmodel and space out the log entry just made
 * via the view xml
 * So this class is a intermediary between the data model and view
 */
public class MvvmViewModel {

    @Inject
    public MvvmViewModel(){}

    public void appendLogEntry(IStationModel iStationModel, TextView logEntryView){
      iStationModel.setLogText(logEntryView.getText().toString());
      logEntryView.setText("");
    }
}
