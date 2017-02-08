package com.fisincorporated.mvpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.dagger.DaggerApplication;
import com.fisincorporated.mvc_mvp_mvvm.R;

import javax.inject.Inject;

public class MvpOSActivity extends AppCompatActivity {

    @Inject
    MvpOSEngineeringPresenter mvpOSEngineeringPresenter;

    @Inject
    MvpOSEngineeringView mvpOSEngineeringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        setTitle("MVP - Observer/Subscriber");

        ((DaggerApplication) getApplication()).getComponent().inject(this);

        // Is this best way to wire up the View and Presenter
        mvpOSEngineeringView.assignView(findViewById(R.id.activity_station_main_layout)).assignViewModel(mvpOSEngineeringPresenter);

        mvpOSEngineeringPresenter.assign(mvpOSEngineeringView);

        mvpOSEngineeringView.onLoad();
    }
}