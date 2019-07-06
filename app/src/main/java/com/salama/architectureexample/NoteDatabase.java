package com.salama.architectureexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    private static NoteDatabase instance;

    // we will use this dao method to access the database operations but we can't call it ela b (instance)
    public abstract NoteDao noteDao();

    // synchronized as if two object call the method in the same time it will synchronize it
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return instance;
    }
    // when the database i create at the first time set these data
    private static RoomDatabase.Callback roomCallBack=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynckTask(instance).execute();

        }
    };

    private static class PopulateAsynckTask extends AsyncTask<Void,Void,Void>{

        NoteDao noteDao;

        private PopulateAsynckTask(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("one","Desc 1",1));
            noteDao.insert(new Note("Two","Desc 2",2));
            noteDao.insert(new Note("Three","Desc 3",3));
            return null;
        }
    }




}
