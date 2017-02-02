package com.raizlabs.mvc_mvp_mvvm.mvvm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.raizlabs.mvc_mvp_mvvm.R;
import com.raizlabs.mvc_mvp_mvvm.common.IStationControl;
import com.raizlabs.mvc_mvp_mvvm.common.IStationModel;
import com.raizlabs.mvc_mvp_mvvm.common.RecyclerViewAdapter;
import com.raizlabs.mvc_mvp_mvvm.common.SwitchChange;
import com.raizlabs.mvc_mvp_mvvm.common.SwitchChangeListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.subjects.PublishSubject;

// TODO - Is implementing IMvvmEngineeringView interface really way to go in MVVM architecture?
public class MvvmEngineeringView implements SwitchChangeListener, IMvvmEngineeringView {

    private View view;

    private IMvvmEngineeringViewModel iEngineeringViewModel;

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

    public MvvmEngineeringView() {}

    public MvvmEngineeringView assignView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
        return this;
    }

    public MvvmEngineeringView assignViewModel(IMvvmEngineeringViewModel iEngineeringViewModel) {
        this.iEngineeringViewModel = iEngineeringViewModel;
        return this;
    }

    public void onLoad() {
        iEngineeringViewModel.getStationModelSetupObservable().subscribe(new Subscriber<IStationModel>() {
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
    public PublishSubject getSwitchChangePublishSubject(){
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
