package com.askjeffreyliu.llogtester;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.askjeffreyliu.llog.LLog;
import com.askjeffreyliu.llog.MobileLog;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private LiveData<List<MobileLog>> logs;

    public ListViewModel(Application application) {
        super(application);
        logs = LLog.getLogs();
    }

    public LiveData<List<MobileLog>> getLogs() {
        return logs;
    }
}
