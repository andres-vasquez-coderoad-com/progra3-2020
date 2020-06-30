package com.andresvasquez.holamundoenclases.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.andresvasquez.holamundoenclases.model.QuarantineTask;

import java.util.List;

@Dao
public interface TasksDao {

    //Insert into quarantine_task VALUES(task)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(QuarantineTask task);

    @Query("DELETE FROM quarantine_task")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateEntry(QuarantineTask task);

    @Query("UPDATE quarantine_task SET finished=:finished WHERE id=:id")
    void updateEntryViaQuery(long id, boolean finished);

    @Query("SELECT * FROM quarantine_task WHERE id=:id")
    LiveData<QuarantineTask> getById(long id);

    @Query("SELECT * FROM quarantine_task ORDER BY id ASC")
    LiveData<List<QuarantineTask>> getAll();

    @Query("SELECT count(0) FROM quarantine_task WHERE finished=:finished")
    LiveData<Integer> getFinishedCount(boolean finished);
}
