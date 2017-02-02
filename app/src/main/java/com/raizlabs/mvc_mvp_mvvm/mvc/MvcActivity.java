package com.raizlabs.mvc_mvp_mvvm.mvc;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raizlabs.mvc_mvp_mvvm.common.IStationModel;
import com.raizlabs.mvc_mvp_mvvm.R;
import com.raizlabs.mvc_mvp_mvvm.common.RecyclerViewAdapter;
import com.raizlabs.mvc_mvp_mvvm.common.StationModel;
import com.raizlabs.mvc_mvp_mvvm.common.SwitchChange;
import com.raizlabs.mvc_mvp_mvvm.common.SwitchChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 'Standard' Android activity but using Butterknife as only enhancement
 */
public class MvcActivity extends AppCompatActivity implements SwitchChangeListener {

    private static final String TAG = MvcActivity.class.getSimpleName();

    private IStationModel iStationModel;

    @BindView(R.id.activity_station_title)
    TextView stationTitle;

    @BindView(R.id.activity_stations_save_log_button)
    Button saveLogButton;

    @BindView(R.id.activity_station_log)
    EditText stationLog;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @BindView(R.id.activity_station_log_layout)
    TextInputLayout stationLogHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp_mvvm);
        ButterKnife.bind(this);
        setTitle("MVC");
        setupStation();
    }

    private void setupStation() {
        iStationModel = StationModel.getStationModel();
        setFieldValues(iStationModel);
        setupRecyclerView(iStationModel);
    }

    //TODO There's got to be better way than using view id twice? Use lambda's rather than Butterknife for events?
    @OnClick(R.id.activity_stations_save_log_button)
    public void onClickSaveLogButton(Button button){
        Log.d(TAG, "Saved Log:" + stationLog.getText());

    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG,  "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

    private void setFieldValues(IStationModel stationInfo) {
        stationTitle.setText(stationInfo.getStationName());
        saveLogButton.setText(stationInfo.getBigButtonName());
        stationLogHint.setHint(stationInfo.getLogHint());
    }

    private void setupRecyclerView(IStationModel stationInfo) {
        stationSwitches.setHasFixedSize(true);
        stationSwitches.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,  stationInfo.getStationControls());
        stationSwitches.setAdapter(recyclerViewAdapter);
    }
}




