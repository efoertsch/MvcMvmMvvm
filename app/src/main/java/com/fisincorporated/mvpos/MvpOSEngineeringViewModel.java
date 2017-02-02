package com.fisincorporated.mvpos;
import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.common.SwitchChange;

import rx.Observable;
import rx.Subscriber;

// Is implementing IMvpOSEngineeringViewModel interface really way to go in MVVM architecture?
public class MvpOSEngineeringViewModel implements IMvpOSEngineeringViewModel {

    private static final String TAG = MvpOSEngineeringViewModel.class.getSimpleName();

    private IStationModel iStationModel;

    Observable<IStationModel> stationModelSetupObervable = Observable.create(
            new Observable.OnSubscribe<IStationModel>() {
                @Override
                public void call(Subscriber<? super IStationModel> sub) {
                    sub.onNext(iStationModel);
                    sub.onCompleted();
                }
            }
    );

    private IMvpOSEngineeringView imvvmEngineeringView;

    public MvpOSEngineeringViewModel() {
        iStationModel = StationModel.getStationModel();
    }

    @Override
    public Observable getStationModelSetupObservable(){
        return stationModelSetupObervable;
    }

    public void assign(IMvpOSEngineeringView imvvmEngineeringView) {
        this.imvvmEngineeringView = imvvmEngineeringView;
        imvvmEngineeringView.getSwitchChangePublishSubject().subscribe(new Subscriber<SwitchChange>() {
            @Override
            public void onCompleted() {
                // Nothing
            }

            @Override
            public void onError(Throwable e) {
                // What to do?
            }

            @Override
            public void onNext(SwitchChange switchChange) {
                Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
                iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
            }
        });

        imvvmEngineeringView.getLogUpdatePublishSubject().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(String logUpdate) {
                Log.d(TAG,"Log update:" + logUpdate);

            }
        });


    }
}
