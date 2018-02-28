package com.fisincorporated.mvvm;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.fisincorporated.common.IStationControl;
import com.fisincorporated.common.IStationModel;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;
import com.fisincorporated.mvc_mvp_mvvm.databinding.ActivityMvvmBinding;

import java.util.List;

import javax.inject.Inject;

// For convenience using IStationModel here also. The methods are used in view binding
public class MvvmViewModel  implements IStationModel, SwitchChangeListener {

    private static final String TAG = MvvmViewModel.class.getSimpleName();

    @Inject
    public IStationModel iStationModel;

    private ActivityMvvmBinding binding;

    @Inject
    public MvvmViewModel(){ }

    public void setBinding(ActivityMvvmBinding binding){
        this.binding = binding;
        binding.setViewModel(this);
        setupRecyclerView(iStationModel);
    }

    private void setupRecyclerView(IStationModel stationInfo) {
        binding.activityStationSwitchesRecyclerview.setHasFixedSize(true);
        binding.activityStationSwitchesRecyclerview.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        RecyclerViewAdapterMvvm recyclerViewAdapter = new RecyclerViewAdapterMvvm(this,  stationInfo.getStationControls());
        binding.activityStationSwitchesRecyclerview.setAdapter(recyclerViewAdapter);
    }

    public void onClickSaveLogButton(View view){
        Log.d(TAG, "Saved Log:" + binding.activityStationLogEntry.getText());
        iStationModel.setLogText(binding.activityStationLogEntry.getText().toString());
        binding.activityStationLogEntry.setText("");
        binding.activityStationLogEntries.setText(iStationModel.getLogText());
    }

    @Override
    public void switchChanged(SwitchChange switchChange) {
        Log.d(TAG,  "Switch changed. Position:" + switchChange.position + " isSelected:" + switchChange.isSelected);
        iStationModel.setStationSwitchValue(switchChange.position, switchChange.isSelected);
    }

    @Override
    public List<IStationControl> getStationControls() {
       return iStationModel.getStationControls();
    }

    @Override
    public String getStationName() {
        return iStationModel.getStationName();
    }

    @Override
    public String getBigButtonName() {
        return iStationModel.getBigButtonName();
    }

    @Override
    public void setLogText(String logText) {
        iStationModel.setLogText(logText);
    }

    @Override
    public String getLogText() {
       return iStationModel.getLogText();
    }

    @Override
    public void setStationSwitchValue(int position, boolean onOff) {
    }
}
