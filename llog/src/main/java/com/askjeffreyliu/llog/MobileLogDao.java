package com.askjeffreyliu.llog;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;



import java.util.List;

@Dao
public interface MobileLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MobileLog log);

    @Query("DELETE FROM mobile_log_table")
    void deleteAll();

    @Query("SELECT * from mobile_log_table ORDER BY id ASC")
    LiveData<List<MobileLog>> getLogs();
}
