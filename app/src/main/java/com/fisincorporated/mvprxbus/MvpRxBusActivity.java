package com.fisincorporated.mvprxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.mvc_mvp_mvvm.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MvpRxBusActivity extends AppCompatActivity {

    @Inject
    IMvpRxBusEngineeringPresenter iMvpRxBusEngineeringPresenter;

    @Inject
    IMvpRxBusEngineeringView iMvpRxBusEngineeringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        setTitle("MVP - Rx Bus");

        iMvpRxBusEngineeringView.assignView(findViewById(R.id.activity_station_main_layout));

        iMvpRxBusEngineeringPresenter.onLoad();
    }

    @Override
    public void onDestroy(){
       super.onDestroy();
        iMvpRxBusEngineeringPresenter.onDestroy();
        iMvpRxBusEngineeringView.onDestroy();
    }
}