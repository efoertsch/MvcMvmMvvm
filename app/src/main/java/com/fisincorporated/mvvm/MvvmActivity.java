package com.fisincorporated.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.StationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvc_mvp_mvvm.databinding.ActivityMvvmBinding;

/**
 * Using Data Binding for MVVM pattern
 */
public class MvvmActivity extends AppCompatActivity implements SwitchChangeListener {

    private static final String TAG = MvvmActivity.class.getSimpleName();

    private ActivityMvvmBinding binding;

    private IStationModel iStationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MVVM - Data Binding");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
       if (binding != null) {
           setupStation();
       }
    }

    private void setupStation() {
        iStationModel = StationModel.getStationModel();
        binding.setStation(iStationModel);
        setupRecyclerView(iStationModel);
    }

    private void setupRecyclerView(IStationModel stationInfo) {
        binding.activityStationSwitchesRecyclerview.setHasFixedSize(true);
        binding.activityStationSwitchesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterMvvm recyclerViewAdapter = new RecyclerViewAdapterMvvm(this,  stationInfo.getStationControls());
        binding.activityStationSwitchesRecyclerview.setAdapter(recyclerViewAdapter);
    }

    public void onClickSaveLogButton(View view){
        Log.d(TAG, "Saved Log:" + binding.activityStationLog.getText());
    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG,  "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

}




