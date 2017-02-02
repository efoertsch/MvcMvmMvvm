package com.raizlabs.mvc_mvp_mvvm.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.raizlabs.mvc_mvp_mvvm.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

import static com.raizlabs.mvc_mvp_mvvm.common.RecyclerViewAdapter.ViewHolder.LAYOUT_RESOURCE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<IStationControl> lineItems;

    private SwitchChangeListener switchChangeListener;

    public RecyclerViewAdapter(SwitchChangeListener switchChangeListener, List<IStationControl> lineItems) {
        this.switchChangeListener = switchChangeListener;
        this.lineItems = lineItems;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_RESOURCE, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.listItemText.setText(lineItems.get(position).getStationControlName());
        holder.listItemSwitch.setChecked(lineItems.get(position).isOnOff());
        holder.listItemSwitch.setTag(position);
        holder.listItemSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchChangeListener != null){
                    switchChangeListener.switchChanged(new SwitchChange((int) compoundButton.getTag(), b));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lineItems.size();
    }

    // TODO - why does this not fire? (So using 'regular' onCheckChangeListenter above)
    @OnCheckedChanged(R.id.list_item_switch)
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (switchChangeListener != null){
            switchChangeListener.switchChanged(new SwitchChange((int) compoundButton.getTag(), b));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected static final int LAYOUT_RESOURCE = R.layout.list_item;

        @BindView(R.id.list_item_text)
        TextView listItemText;

        @BindView(R.id.list_item_switch)
        public Switch listItemSwitch;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
