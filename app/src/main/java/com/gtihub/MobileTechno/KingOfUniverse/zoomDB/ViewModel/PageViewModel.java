package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Pojo.Note;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Repository.NoteRepository;

import java.util.List;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */

public class PageViewModel extends AndroidViewModel {
    /**
     * Live Data Instance
     */
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public PageViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

}
