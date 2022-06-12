package com.edify.app.ui.studentSignOut;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentSignOutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StudentSignOutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}