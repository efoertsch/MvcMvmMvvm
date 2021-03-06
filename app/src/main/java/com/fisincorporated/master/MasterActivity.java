package com.fisincorporated.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fisincorporated.mvc.MvcActivity;
import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvp.MvpActivity;
import com.fisincorporated.mvprxbus.MvpRxBusActivity;
import com.fisincorporated.mvvm.MvvmActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MasterActivity extends AppCompatActivity {

    @BindView(R.id.activity_master_mvc_button)
    Button mvcButton;

    @BindView(R.id.activity_master_mvp_button)
    Button mvpButton;

    @BindView(R.id.activity_master_mvp_rxbus_button)
    Button mvvmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_master_mvc_button)
    public void onClickMvcButton(View view){
        startActivity(MvcActivity.class);
    }

    @OnClick(R.id.activity_master_mvp_button)
    public void onClickMvpButton(View view){
        startActivity(MvpActivity.class);
    }

    @OnClick(R.id.activity_master_mvp_rxbus_button)
    public void onClickMvpOSButton(View view){
        startActivity(MvpRxBusActivity.class);
    }

    @OnClick(R.id.activity_master_mvvm_button)
    public void onClickMvvmButton(View view){
        startActivity(MvvmActivity.class);
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


}
