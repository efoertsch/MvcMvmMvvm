package com.fisincorporated.mvpos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.mvc_mvp_mvvm.R;

public class MvpOSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        setTitle("MVP - Observer/Subscriber");
        MvpOSEngineeringPresenter mvpOSEngineeringPresenter = new MvpOSEngineeringPresenter();

        MvpOSEngineeringView mvpOSEngineeringView = (new MvpOSEngineeringView()).assignView(findViewById(R.id.activity_station_main_layout))
                .assignViewModel(mvpOSEngineeringPresenter);

        mvpOSEngineeringPresenter.assign(mvpOSEngineeringView);

        mvpOSEngineeringView.onLoad();
    }
}