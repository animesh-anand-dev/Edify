package com.edify.app.ui.teacherSignOut;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeacherSignOutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TeacherSignOutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}