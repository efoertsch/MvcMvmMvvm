package com.fisincorporated.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvc_mvp_mvvm.databinding.MvvmDataBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Using Data Binding for MVVM pattern
 */
//Using IStationModel update to log entries doesn't occur as binding of StationModel values not seen
// As reference https://stackoverflow.com/questions/44301960/how-to-use-android-data-binding-to-bind-an-interface

public class MvvmActivity extends AppCompatActivity implements SwitchChangeListener {

    private static final String TAG = MvvmActivity.class.getSimpleName();

    private MvvmDataBinding binding;

    @Inject
    public StationModel stationModel;

    @Inject
    public MvvmViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setTitle("MVVM - Data Binding");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        binding.setDataModel(stationModel);
        binding.setViewModel(mvvmViewModel);
        setupRecyclerView(stationModel);
    }

    private void setupRecyclerView(IStationModel iStationModel) {
        binding.activityStationSwitchesRecyclerview.setHasFixedSize(true);
        binding.activityStationSwitchesRecyclerview.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        RecyclerViewAdapterMvvm recyclerViewAdapter = new RecyclerViewAdapterMvvm(this, iStationModel.getStationControls());
        binding.activityStationSwitchesRecyclerview.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG, "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        stationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

}




