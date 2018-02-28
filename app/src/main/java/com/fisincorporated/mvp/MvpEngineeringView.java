package com.fisincorporated.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.common.IStationControl;
import com.fisincorporated.common.RecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpEngineeringView implements IMvpEngineeringView {

    View view;
    IMvpEngineeringPresenter iMvpEngineeringPresenter;

    @Inject
    public MvpEngineeringView(){}

    @Override
    public MvpEngineeringView assignView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
        return this;
    }

    @Override
    public MvpEngineeringView assignPresenter(IMvpEngineeringPresenter iMvpEngineeringPresenter){
        this.iMvpEngineeringPresenter = iMvpEngineeringPresenter;
        return this;
    }

    @BindView(R.id.activity_station_title)
    TextView stationTitle;

    @BindView(R.id.activity_stations_save_log_button)
    Button saveLogButton;

    @OnClick(R.id.activity_stations_save_log_button)
    void saveLog(){
        iMvpEngineeringPresenter.saveStationLogEntry(stationLog.getText().toString());
        stationLog.setText("");
    }

    @BindView(R.id.activity_station_log_entry)
    EditText stationLog;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @BindView(R.id.activity_station_log_entries)
    TextView stationLogEntries;

    @Override
    public void setStationName(String stationName) {
        stationTitle.setText(stationName);
    }

    @Override
    public void setBigButtonName(String bigButtonName) {
        saveLogButton.setText(bigButtonName);
    }

    @Override
    public void setStationEngineeringControls(List<IStationControl> stationControls) {
        stationSwitches.setHasFixedSize(true);
        stationSwitches.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //The Presenter interface is being called to handle the switch changes.
        // Should the View get changes and call presenter is different way?
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(iMvpEngineeringPresenter, stationControls);
        stationSwitches.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void setStationLogEntries(String stationLog){
        stationLogEntries.setText(stationLog);
    }

}
