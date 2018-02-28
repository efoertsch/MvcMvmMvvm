package com.fisincorporated.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.mvc_mvp_mvvm.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MvpActivity extends AppCompatActivity {

    @Inject
    IMvpEngineeringPresenter iMvpEngineeringPresenter;

    @Inject
    IMvpEngineeringView iMvpEngineeringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp);
        setTitle("MVP");

        iMvpEngineeringView.assignView(findViewById(R.id.activity_station_main_layout)).assignPresenter(iMvpEngineeringPresenter);

        // Could have left view interface in activity. Carrying things a bit to far?
        iMvpEngineeringPresenter.assignEngineeringView(iMvpEngineeringView);

        // Is this really best spot to tell Presenter ok to start loading data for screen display?
        iMvpEngineeringPresenter.onLoad();
    }

}
