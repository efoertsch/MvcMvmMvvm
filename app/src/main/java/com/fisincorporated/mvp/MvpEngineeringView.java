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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpEngineeringView implements IMvpEngineeringView {

    View view;
    IMvpEngineeringPresenter iMvpEngineeringPresenter;

    public MvpEngineeringView(){}

    public MvpEngineeringView assignView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
        return this;
    }

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
        iMvpEngineeringPresenter.saveStationLog(stationLog.getText().toString());
    }

    @BindView(R.id.activity_station_log)
    EditText stationLog;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @Override
    public void setStationName(String stationName) {
        stationTitle.setText(stationName);
    }

    @Override
    public void setBigButtonName(String bigButtonName) {
        saveLogButton.setText(bigButtonName);
    }

    @Override
    public void setLogHint(String logHint) {
        stationLog.setHint(logHint);
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

}
