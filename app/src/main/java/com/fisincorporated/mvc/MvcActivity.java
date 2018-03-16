package com.fisincorporated.mvc;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.common.RecyclerViewAdapter;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 'Standard' Android activity but using Butterknife as only enhancement
 * No Dagger injection
 */
public class MvcActivity extends AppCompatActivity implements SwitchChangeListener {

    private static final String TAG = MvcActivity.class.getSimpleName();

    private IStationModel iStationModel;

    @BindView(R.id.activity_station_title)
    TextView stationTitle;

    @BindView(R.id.activity_stations_save_log_button)
    Button saveLogButton;

    @BindView(R.id.activity_station_log_entry)
    EditText stationLog;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @BindView(R.id.activity_station_log_entry_layout)
    TextInputLayout stationLogHint;

    @BindView(R.id.activity_station_log_entries)
    TextView stationLogEnties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        ButterKnife.bind(this);
        setTitle("MVC");
        setupStation();
    }

    private void setupStation() {
        iStationModel = StationModel.getIStationModel();
        setFieldValues();
        setupRecyclerView(iStationModel);
    }

    //TODO There's got to be better way than using view id twice? Use lambda's rather than Butterknife for events?
    @OnClick(R.id.activity_stations_save_log_button)
    public void onClickSaveLogButton(Button button){
        Log.d(TAG, "Saved Log:" + stationLog.getText());
        iStationModel.setLogText(stationLog.getText().toString());
        displayCurrentLogEntries();

    }

    private void displayCurrentLogEntries() {
        stationLogEnties.setText(iStationModel.getLogText());
        stationLog.setText(null);
    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG,  "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
        displayCurrentLogEntries();

    }

    private void setFieldValues() {
        stationTitle.setText(iStationModel.getStationName());
        saveLogButton.setText(iStationModel.getLogButtonText());
        displayCurrentLogEntries();
    }

    private void setupRecyclerView(IStationModel stationInfo) {
        stationSwitches.setHasFixedSize(true);
        stationSwitches.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,  stationInfo.getStationControls());
        stationSwitches.setAdapter(recyclerViewAdapter);
    }
}




