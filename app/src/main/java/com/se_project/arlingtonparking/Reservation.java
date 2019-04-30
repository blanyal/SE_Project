package com.se_project.arlingtonparking;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Reservation {
    @PrimaryKey
    @NonNull
    public int uid;

    @ColumnInfo(name = "datetime")
    public String datetime;

    @ColumnInfo(name = "type")
    public int type;

    @ColumnInfo(name = "options")
    public int options;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "no_show")
    public boolean no_show;

    @ColumnInfo(name = "overdue")
    public boolean overdue;

    @ColumnInfo(name = "reserved")
    public boolean reserved;

    @ColumnInfo(name = "area")
    public String area;

    public Reservation(int uid, String datetime, int type, int options, String username, boolean no_show,
                       boolean overdue, boolean reserved, String area) {
        this.uid = uid;
        this.datetime = datetime;
        this.type = type;
        this.options = options;
        this.username = username;
        this.no_show = no_show;
        this.overdue = overdue;
        this.reserved = reserved;
        this.area = area;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNo_show() {
        return no_show;
    }

    public void setNo_show(boolean no_show) {
        this.no_show = no_show;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
