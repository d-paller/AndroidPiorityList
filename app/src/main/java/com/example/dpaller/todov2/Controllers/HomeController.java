package com.example.dpaller.todov2.Controllers;

import android.content.Context;

import com.example.dpaller.todov2.Data.AppDB;
import com.example.dpaller.todov2.Models.TodoItem;

import java.util.List;

/**
 * Created by dpall on 11/18/2017.
 */

public class HomeController {

    private AppDB db;

    public HomeController(Context context){
        db = AppDB.getDB(context);
    }

    public void addItem(TodoItem item){
        db.toDoDao().addItem(item);
    }

    public List<TodoItem> getAllItems(){
        return db.toDoDao().getAllItems();
    }

    public List<TodoItem> getAllOfPriority(int priority){
        return db.toDoDao().getAllOfPriority(priority);
    }

    public void updateItem(TodoItem item){
        db.toDoDao().updateItem(item);
    }

    public void markAsDone(int itemId){
        db.toDoDao().markAsDone(itemId);
    }

}
