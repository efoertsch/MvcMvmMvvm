package com.fisincorporated.mvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.fisincorporated.common.IStationControl;
import com.fisincorporated.common.SwitchChange;
import com.fisincorporated.common.SwitchChangeListener;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvc_mvp_mvvm.databinding.ListItemMvvmBinding;

import java.util.List;


public class RecyclerViewAdapterMvvm extends RecyclerView.Adapter<RecyclerViewAdapterMvvm.ViewHolder> {
    private List<IStationControl> iStationControls;

    private SwitchChangeListener switchChangeListener;

    public RecyclerViewAdapterMvvm(SwitchChangeListener switchChangeListener, List<IStationControl> iStationControls) {
        this.switchChangeListener = switchChangeListener;
        this.iStationControls = iStationControls;
    }

    @Override
    public RecyclerViewAdapterMvvm.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemMvvmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), ViewHolder.LAYOUT_RESOURCE, parent, false);
        return new RecyclerViewAdapterMvvm.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterMvvm.ViewHolder holder, int position) {
        holder.binding.setControl(iStationControls.get(position));
        holder.binding.listItemSwitch.setTag(position);
        holder.binding.listItemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchChangeListener != null) {
                    switchChangeListener.switchChanged(new SwitchChange((int) compoundButton.getTag(), b));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return iStationControls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected static final int LAYOUT_RESOURCE = R.layout.list_item_mvvm;

        private ListItemMvvmBinding binding;

        public ViewHolder(ListItemMvvmBinding bindingView) {
            super(bindingView.getRoot());
            binding = bindingView;
        }

    }
}
