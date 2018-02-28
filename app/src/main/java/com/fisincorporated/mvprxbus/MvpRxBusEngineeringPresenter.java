package com.fisincorporated.mvprxbus;

import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.mvprxbus.messages.LogEntries;
import com.fisincorporated.mvprxbus.messages.LogUpdate;
import com.jakewharton.rxrelay2.PublishRelay;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

// Is implementing IMvpRxBusEngineeringPresenter interface really way to go in MVVM architecture?
public class MvpRxBusEngineeringPresenter implements IMvpRxBusEngineeringPresenter, IOnDestroy {

    private static final String TAG = MvpRxBusEngineeringPresenter.class.getSimpleName();

    private final PublishRelay<Object> publishRelay;

    private IStationModel iStationModel;

    private Disposable publishRelayDisposable;

    @Inject
    public MvpRxBusEngineeringPresenter(IStationModel iStationModel, PublishRelay<Object> publishRelay) {
        this.iStationModel = iStationModel;
        this.publishRelay = publishRelay;
        // Add bus subscriber
        publishRelay.subscribe(publishRelayObserver);
    }

    @Override
    public IMvpRxBusEngineeringPresenter onLoad() {
       publishRelay.accept((iStationModel));
       return this;
    }

    private Observer<Object> publishRelayObserver = new Observer<Object>() {
        @Override
        public void onSubscribe(Disposable disposable) {
            publishRelayDisposable = disposable;
        }

        @Override
        public void onNext(Object o) {
            if (o instanceof SwitchChange) {
                SwitchChange switchChange = (SwitchChange) o;
                Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
                iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
                return;
            }
            if (o instanceof LogUpdate) {
                String logUpdate = ((LogUpdate) o).getLogUpdate();
                Log.d(TAG, "Log update:" + logUpdate);
                iStationModel.setLogText(logUpdate);
                publishRelay.accept(new LogEntries(iStationModel.getLogText()));
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
