package com.fisincorporated.mvprxbus;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fisincorporated.common.IStationControl;
import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.RecyclerViewAdapter;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvprxbus.messages.LogEntries;
import com.fisincorporated.mvprxbus.messages.LogUpdate;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MvpRxBusEngineeringView implements IMvpRxBusEngineeringView, SwitchChangeListener {

    private static final String TAG = MvpRxBusEngineeringView.class.getSimpleName();

    private View view;

    private PublishRelay<Object> publishRelay;

    private Disposable publishRelayDisposable;

    @BindView(R.id.activity_station_title)
    TextView stationTitle;

    @BindView(R.id.activity_stations_save_log_button)
    Button saveLogButton;

    @BindView(R.id.activity_station_log_entry)
    EditText stationLogEntry;

    @BindView(R.id.activity_station_switches_recyclerview)
    RecyclerView stationSwitches;

    @BindView(R.id.activity_station_log_entries)
    TextView stationLogEntries;


    @Override
    public void switchChanged(SwitchChange switchChange) {
        publishRelay.accept(switchChange);
    }

    // Send log updates onto bus (note just sending as String,
    @OnClick(R.id.activity_stations_save_log_button)
    void saveLog() {
        publishRelay.accept(new LogUpdate(stationLogEntry.getText().toString()));
        stationLogEntry.setText("");
    }

    @Inject
    public MvpRxBusEngineeringView(PublishRelay<Object> publishRelay) {
        this.publishRelay = publishRelay;
        publishRelay.subscribe(publishRelayObserver);
    }

    public IMvpRxBusEngineeringView assignView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);
        return this;
    }

    private void showLogEntries(String logEntries) {
        stationLogEntries.setText(logEntries);
    }

    private void setStationEngineeringControls(List<IStationControl> stationControls) {
        stationSwitches.setHasFixedSize(true);
        stationSwitches.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, stationControls);
        stationSwitches.setAdapter(recyclerViewAdapter);
    }

    private Observer<Object> publishRelayObserver = new Observer<Object>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            publishRelayDisposable = disposable;
        }

        @Override
        public void onNext(Object o) {
            if (o instanceof IStationModel) {
                IStationModel iStationModel = (IStationModel) o;
                stationTitle.setText(iStationModel.getStationName());
                saveLogButton.setText(iStationModel.getLogButtonText());
                stationLogEntries.setText(iStationModel.getLogText());
                setStationEngineeringControls(iStationModel.getStationControls());
                return;
            }
            if (o instanceof LogEntries){
                showLogEntries(((LogEntries) o).getLogEntries());
            }
        }

        @Override
        public void onError(Throwable e) {
            // Big Trouble - PublishRelay should never throw
            Log.e(TAG, "PublishRelay throwing error:" + e.toString());
            // TODO Do something more
        }

        @Override
        public void onComplete() {
            // Big Trouble - PublishRelay should never call
            Log.e(TAG, "PublishRelay onComplete Thrown");
            // TODO Do something more
        }

    };

    @Override
    public void onDestroy() {
        if (publishRelayDisposable != null){
            publishRelayDisposable.dispose();
        }
    }
}
