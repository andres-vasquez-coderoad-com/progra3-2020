package com.andresvasquez.holamundoenclases.repository.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;
import com.andresvasquez.holamundoenclases.repository.local.db.TasksDb;

import java.util.List;

public class TasksRepository {
    private TasksDb db;

    public TasksRepository(Application application) {
        db = TasksDb.getDatabase(application);
    }

    public void insert(final QuarantineTask task) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db.tasksDao().insert(task);
            }
        });
        thread.start();
    }

    public void updateEntry(final QuarantineTask task) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db.tasksDao().updateEntry(task);
            }
        });
        thread.start();
    }

    public void deleteAll() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db.tasksDao().deleteAll();
            }
        });
        thread.start();
    }

    public LiveData<List<QuarantineTask>> getAll() {
        return db.tasksDao().getAll();
    }

    public LiveData<QuarantineTask> getById(long id) {
        return db.tasksDao().getById(id);
    }

    public LiveData<Integer> getFinishedCount() {
        return db.tasksDao().getFinishedCount(true);
    }
}
