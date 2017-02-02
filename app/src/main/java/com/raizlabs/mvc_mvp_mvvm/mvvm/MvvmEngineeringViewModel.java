package com.raizlabs.mvc_mvp_mvvm.mvvm;
import android.util.Log;

import com.raizlabs.mvc_mvp_mvvm.common.IStationModel;
import com.raizlabs.mvc_mvp_mvvm.common.StationModel;
import com.raizlabs.mvc_mvp_mvvm.common.SwitchChange;

import rx.Observable;
import rx.Subscriber;

// Is implementing IMvvmEngineeringViewModel interface really way to go in MVVM architecture?
public class MvvmEngineeringViewModel implements IMvvmEngineeringViewModel {

    private static final String TAG = MvvmEngineeringViewModel.class.getSimpleName();

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

    private IMvvmEngineeringView imvvmEngineeringView;

    public MvvmEngineeringViewModel() {
        iStationModel = StationModel.getStationModel();
    }

    @Override
    public Observable getStationModelSetupObservable(){
        return stationModelSetupObervable;
    }

    public void assign(IMvvmEngineeringView imvvmEngineeringView) {
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
