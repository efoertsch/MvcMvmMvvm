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
        MvpOSEngineeringViewModel mvvmEngineeringViewModel = new MvpOSEngineeringViewModel();

        // Is this best way to wire up the View and ViewModel?
        MvpOSEngineeringView mvvmEngineeringView = (new MvpOSEngineeringView()).assignView(findViewById(R.id.activity_station_main_layout))
                .assignViewModel(mvvmEngineeringViewModel);

        // Is there a better way to have ViewModel subscribe to View events?
        mvvmEngineeringViewModel.assign(mvvmEngineeringView);

        // Tell View to load station info. Don't think this is correct spot for this though.
        mvvmEngineeringView.onLoad();
    }
}