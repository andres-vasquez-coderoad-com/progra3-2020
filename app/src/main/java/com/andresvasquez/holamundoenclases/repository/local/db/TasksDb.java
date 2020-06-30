package com.andresvasquez.holamundoenclases.repository.local.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;
import com.andresvasquez.holamundoenclases.repository.local.dao.TasksDao;

@Database(entities = {QuarantineTask.class}, version = 2)
public abstract class TasksDb extends RoomDatabase {

    public abstract TasksDao tasksDao();
    private static volatile TasksDb INSTANCE;

    static public TasksDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TasksDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TasksDb.class, "tasks_database_2.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
