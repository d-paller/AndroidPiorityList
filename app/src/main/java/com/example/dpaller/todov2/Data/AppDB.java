package com.example.dpaller.todov2.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.dpaller.todov2.Models.TodoItem;

@Database(entities = {TodoItem.class}, version = 2, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    private static AppDB INSTANCE;

    public abstract ToDoDao toDoDao();

    public static AppDB getDB(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDB.class, "AppDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyDB(){
        INSTANCE = null;
    }
}
