package com.fisincorporated.mvpos;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.common.IStationControl;
import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.RecyclerViewAdapter;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.subjects.PublishSubject;

public class MvpOSEngineeringView implements SwitchChangeListener, IMvpOSEngineeringView {

    private View view;

    private IMvpOSEngineeringPresenter iMvpOSEngineeringPresenter;

    private PublishSubject<SwitchChange> switchChangePublishSubject = PublishSubject.create();

    private PublishSubject<String> logUpdatePublishSubject = PublishSubject.create();

    @BindView(R.id.activity_station_title)
    TextView stationTitle;

    @BindView(R.id.activity_stations_save_log_button)
    Button saveLogButton;

    @BindView(R.id.activity_station_log)
    EditText stationLog;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @Override
    public void switchChanged(SwitchChange switchChange) {
        switchChangePublishSubject.onNext(switchChange);
    }

    @OnClick(R.id.activity_stations_save_log_button)
    void saveLog() {
        logUpdatePublishSubject.onNext(stationLog.getText().toString());
    }

    public MvpOSEngineeringView() {
    }

    public MvpOSEngineeringView assignView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
        return this;
    }

    public MvpOSEngineeringView assignViewModel(IMvpOSEngineeringPresenter iEngineeringViewModel) {
        this.iMvpOSEngineeringPresenter = iEngineeringViewModel;
        return this;
    }

    public void onLoad() {
        iMvpOSEngineeringPresenter.getStationModelSetupObservable().subscribe(new Subscriber<IStationModel>() {
            @Override
            public void onNext(IStationModel stationModel) {
                stationTitle.setText(stationModel.getStationName());
                saveLogButton.setText(stationModel.getBigButtonName());
                stationLog.setHint(stationModel.getLogHint());
                setStationEngineeringControls(stationModel.getStationControls());
            }

            @Override
            public void onCompleted() {
                // Nothing
            }

            @Override
            public void onError(Throwable e) {
                // Display some error to user
            }

        });
    }

    @Override
    public PublishSubject getSwitchChangePublishSubject() {
        return switchChangePublishSubject;
    }

    @Override
    public PublishSubject getLogUpdatePublishSubject() {
        return logUpdatePublishSubject;
    }

    public void setStationEngineeringControls(List<IStationControl> stationControls) {
        stationSwitches.setHasFixedSize(true);
        stationSwitches.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, stationControls);
        stationSwitches.setAdapter(recyclerViewAdapter);
    }
}
