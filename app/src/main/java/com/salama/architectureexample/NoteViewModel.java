package com.salama.architectureexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteResprosity noteResprosity;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteResprosity=new NoteResprosity(application);
        allNotes=noteResprosity.getAllNotes();
    }
    public void insert(Note note){
        noteResprosity.insert(note);
    }

    public void update(Note note){
        noteResprosity.update(note);
    }

    public void delete(Note note)
    {
        noteResprosity.delete(note);
    }

    public void deleteAllNotes()
    {
        noteResprosity.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return allNotes;
    }
}
