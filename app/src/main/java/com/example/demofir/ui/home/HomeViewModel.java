package com.example.demofir.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import java.util.List;


public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> uname,identifier;
    public HomeViewModel() {
        uname = new MutableLiveData<>();
        identifier = new MutableLiveData<>();
        uname.setValue("食品库入库清单");
    }
    public LiveData<String> getText() {
        return uname;
    }

}
