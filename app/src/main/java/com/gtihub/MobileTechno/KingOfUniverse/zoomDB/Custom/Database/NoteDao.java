package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Custom.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo.Note;

import java.util.List;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    LiveData<List<Note>> getAllNotes();
}