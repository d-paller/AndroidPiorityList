package com.example.dpaller.todov2.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.dpaller.todov2.Models.TodoItem;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addItem(TodoItem item);

    @Query("select * from todoitem")
    List<TodoItem> getAllItems();

    @Query("select * from TodoItem where _priority = :priority and _isDone != 1")
    List<TodoItem> getAllOfPriority(int priority);

    @Query("select * from TodoItem " +
            " where _priority = (select min(_priority) from TodoItem)" +
            " and _isDone = 0")
    List<TodoItem> getAllHighestPriority();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(TodoItem item);

    @Query("update TodoItem set _isDone = 1 where _itemId = :itemId")
    void markAsDone(Integer itemId);

    @Query("delete from TodoItem")
    void clearDB();
}
