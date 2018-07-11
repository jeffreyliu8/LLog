package com.askjeffreyliu.llog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "mobile_log_table")
public class MobileLog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "message")
    private String message;

    @NonNull
    @ColumnInfo(name = "thread")
    private String thread;

    @NonNull
    @ColumnInfo(name = "logLevel")
    private int logLevel;

    @NonNull
    @ColumnInfo(name = "synced")
    private boolean synced;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public MobileLog(@NonNull String message, @NonNull String thread, @NonNull int logLevel) {
        this.message = message;
        this.thread = thread;
        this.logLevel = logLevel;
        synced = false;
        timestamp = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getThread() {
        return thread;
    }

    public void setThread(@NonNull String thread) {
        this.thread = thread;
    }


    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    @NonNull
    public boolean isSynced() {
        return synced;
    }

    public void setSynced(@NonNull boolean synced) {
        this.synced = synced;
    }

    @NonNull
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull long timestamp) {
        this.timestamp = timestamp;
    }
}