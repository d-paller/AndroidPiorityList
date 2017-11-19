package com.example.dpaller.todov2.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.dpaller.todov2.Controllers.HomeController;
import com.example.dpaller.todov2.Models.TodoItem;
import com.example.dpaller.todov2.R;
import com.example.dpaller.todov2.Services.TodoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {

    ArrayList<TodoItem> items;
    private HomeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initialize controller
        controller = new HomeController(getApplicationContext());

        // set recycler view
        RecyclerView rvTodos = (RecyclerView) findViewById(R.id.RecView);

        new AsyncTask<Void, Void, Integer>(){

            @Override
            protected Integer doInBackground(Void... voids) {
                // Initialize contacts
                controller.addItem(new TodoItem("fake1", "tag1", 1, "description blah blah blah") );
                controller.addItem(new TodoItem("fake2", "tag2", 1, "description blah blah blah") );
                controller.addItem(new TodoItem("fake3", "tag3", 2, "description blah blah blah") );
                return 1;
            }
        }.execute();

        List<TodoItem> list = null;
        try {
            list = new AsyncTask<Void, Void, List<TodoItem>>(){

                @Override
                protected List<TodoItem> doInBackground(Void... voids) {
                    return controller.getAllItems();
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // Create adapter passing in the sample user data
        TodoAdapter adapter = new TodoAdapter(this, list);

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
