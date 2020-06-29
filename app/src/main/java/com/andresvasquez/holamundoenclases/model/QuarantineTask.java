package com.andresvasquez.holamundoenclases.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quarantine_task")
public class QuarantineTask {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "duration")
    private String duration;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "finished")
    private boolean finished;

    @ColumnInfo(name = "image")
    private int image;

    public QuarantineTask(long id, String name, String duration, String details, int image) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.details = details;
        this.finished = false;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
