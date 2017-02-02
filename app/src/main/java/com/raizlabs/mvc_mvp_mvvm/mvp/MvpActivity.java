package com.raizlabs.mvc_mvp_mvvm.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.raizlabs.mvc_mvp_mvvm.R;

public class MvpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp_mvvm);
        setTitle("MVP");
        MvpEngineeringPresenter mvpEngineeringPresenter = new MvpEngineeringPresenter();

        // Is this best way to wire up Presenter and View?
        MvpEngineeringView mvpEngineeringView = (new MvpEngineeringView())
                .assignView(findViewById(R.id.activity_station_main_layout))
                .assignPresenter(mvpEngineeringPresenter);

        mvpEngineeringPresenter.assignEngineeringView(mvpEngineeringView);

        // Is this really best spot to tell Presenter ok to start loading data for screen display?
        mvpEngineeringPresenter.onLoad();
    }

}
