package com.fisincorporated.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.dagger.DaggerApplication;
import com.fisincorporated.mvc_mvp_mvvm.R;

import javax.inject.Inject;

public class MvpActivity extends AppCompatActivity {

    @Inject
    MvpEngineeringPresenter mvpEngineeringPresenter;

    @Inject
    MvpEngineeringView mvpEngineeringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        setTitle("MVP");

        ((DaggerApplication) getApplication()).getComponent().inject(this);

        mvpEngineeringView.assignView(findViewById(R.id.activity_station_main_layout)).assignPresenter(mvpEngineeringPresenter);

        // Could have left view interface in activity. Carrying things a bit to far?
        mvpEngineeringPresenter.assignEngineeringView(mvpEngineeringView);

        // Is this really best spot to tell Presenter ok to start loading data for screen display?
        mvpEngineeringPresenter.onLoad();
    }

}
