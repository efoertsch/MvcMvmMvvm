package com.fisincorporated.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fisincorporated.mvc_mvp_mvvm.R;
import com.fisincorporated.mvc_mvp_mvvm.databinding.ActivityMvvmBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Using Data Binding for MVVM pattern
 */
public class MvvmActivity extends AppCompatActivity {

    private static final String TAG = MvvmActivity.class.getSimpleName();

    private ActivityMvvmBinding binding;

    @Inject
    public MvvmViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setTitle("MVVM - Data Binding");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);
        mvvmViewModel.setBinding(binding);

    }

}




