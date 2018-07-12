package com.askjeffreyliu.llog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    @Nullable
    @ColumnInfo(name = "throwable")
    private String throwable;

    @NonNull
    @ColumnInfo(name = "synced")
    private boolean synced;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public MobileLog(@NonNull String message, @NonNull String thread, @NonNull int logLevel, @Nullable String throwable) {
        this.message = message;
        this.thread = thread;
        this.logLevel = logLevel;
        this.throwable = throwable;
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

    @NonNull
    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(@NonNull int logLevel) {
        this.logLevel = logLevel;
    }

    @Nullable
    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(@Nullable String throwable) {
        this.throwable = throwable;
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