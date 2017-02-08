package com.fisincorporated.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvc_mvp_mvvm.databinding.ActivityMvvmBinding;

/**
 * Using Data Binding for MVVM pattern
 */
public class MvvmActivity extends AppCompatActivity  {

    private static final String TAG = MvvmActivity.class.getSimpleName();

    private ActivityMvvmBinding binding;

    public MvvmViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("MVVM - Data Binding");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        mvvmViewModel = new MvvmViewModel();

        mvvmViewModel.setBinding(binding);

    }

}




