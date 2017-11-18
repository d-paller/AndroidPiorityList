package com.example.dpaller.todov2.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.dpaller.todov2.Models.TodoItem;
import com.example.dpaller.todov2.R;
import com.example.dpaller.todov2.Services.TodoAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ArrayList<TodoItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvTodos = (RecyclerView) findViewById(R.id.RecView);

        // Initialize contacts
        items = new ArrayList<TodoItem>();
        items.add(new TodoItem("fake1", "tag1", 1, "description blah blah blah") );
        items.add(new TodoItem("fake2", "tag2", 1, "description blah blah blah") );
        items.add(new TodoItem("fake3", "tag3", 2, "description blah blah blah") );

        // Create adapter passing in the sample user data
        //
        TodoAdapter adapter = new TodoAdapter(this, items);
        // Attach the adapter to the recyclerview to populate items
        rvTodos.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvTodos.setLayoutManager(llm);
    }

    public void AddNewItem(View view){
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }
}
