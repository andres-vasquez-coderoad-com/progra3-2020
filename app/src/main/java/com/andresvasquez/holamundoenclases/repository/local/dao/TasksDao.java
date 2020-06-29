package com.andresvasquez.holamundoenclases.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import java.util.List;

@Dao
public interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(QuarantineTask task);

    @Query("DELETE FROM quarantine_task")
    void deleteAll();

    @Query("SELECT * FROM quarantine_task ORDER BY id ASC")
    LiveData<List<QuarantineTask>> getAll();
}
