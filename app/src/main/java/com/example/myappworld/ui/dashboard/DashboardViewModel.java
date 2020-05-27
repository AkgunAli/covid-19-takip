package com.example.myappworld.ui.dashboard;

import android.webkit.WebView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myappworld.R;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<String> mText;



    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("COVİD-19 MAPS");

    }

    public LiveData<String> getText() {
        return mText;
    }
}