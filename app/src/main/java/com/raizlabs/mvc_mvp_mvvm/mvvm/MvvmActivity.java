package com.raizlabs.mvc_mvp_mvvm.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.raizlabs.mvc_mvp_mvvm.R;

public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc_mvp_mvvm);
        setTitle("MVVM");
        MvvmEngineeringViewModel mvvmEngineeringViewModel = new MvvmEngineeringViewModel();

        // Is this best way to wire up the View and ViewModel?
        MvvmEngineeringView mvvmEngineeringView = (new MvvmEngineeringView()).assignView(findViewById(R.id.activity_station_main_layout))
                .assignViewModel(mvvmEngineeringViewModel);

        // Is there a better way to have ViewModel subscribe to View events?
        mvvmEngineeringViewModel.assign(mvvmEngineeringView);

        // Tell View to load station info. Don't think this is correct spot for this though.
        mvvmEngineeringView.onLoad();
    }
}