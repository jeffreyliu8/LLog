package com.askjeffreyliu.llog;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;



@Database(entities = {MobileLog.class}, version = 1, exportSchema = false)
public abstract class MobileLogRoomDatabase extends RoomDatabase {

    public abstract MobileLogDao mobileLogDao();

    private static MobileLogRoomDatabase INSTANCE;

    public static MobileLogRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobileLogRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MobileLogRoomDatabase.class, "mobile_log_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}