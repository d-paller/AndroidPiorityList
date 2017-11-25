package com.example.dpaller.todov2.Views;

import android.app.Activity;
import android.arch.persistence.room.Update;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dpaller.todov2.Controllers.HomeController;
import com.example.dpaller.todov2.Models.TodoItem;
import com.example.dpaller.todov2.R;
import com.example.dpaller.todov2.Services.TodoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
    List<TodoItem> list = null;
    ArrayList<TodoItem> items;
    TodoAdapter adapter;
    private HomeController controller;
    private Context _context;
    RecyclerView rvTodos;
    Integer lastItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        _context = this;

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        // initialize controller
        controller = new HomeController(getApplicationContext());

        // set recycler view
        rvTodos = (RecyclerView) findViewById(R.id.RecView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                getCorrectItems(item.getItemId());

                // Create adapter passing in the sample user data
                adapter = new TodoAdapter(_context, list);

                // Attach the adapter to the recyclerview to populate items
                rvTodos.setAdapter(adapter);
                return true;
            }
        });

        lastItemId = R.id.show_all;
        getCorrectItems(lastItemId);
        UpdateView(list);

    }

    public void UpdateView(List<TodoItem> list){

        // Create adapter passing in the sample user data
        adapter = new TodoAdapter(_context, list);

        // Attach the adapter to the recyclerview to populate items
        rvTodos.setAdapter(adapter);

        // Set layout manager to position the items
        LinearLayoutManager llm = new LinearLayoutManager(_context);
        rvTodos.setLayoutManager(llm);
    }

    public void AddNewItem(View view){
        Intent intent = new Intent(this, ItemActivity.class);
        startActivity(intent);
    }

    public void MarkAsComplete(View view) {

            new AsyncTask<Void, Void, Integer>() {

                @Override
                protected Integer doInBackground(Void... voids) {
                    TextView tv = findViewById(R.id.title);
                    controller.markAsDone(Integer.parseInt(tv.getTag().toString()));
                    return 1;
                }
            }.execute();

            getCorrectItems(lastItemId);
            UpdateView(list);
    }

    private void getCorrectItems(Integer itemId){
        switch(itemId){

            case R.id.show_priority:
                lastItemId = itemId;
                try {
                    list = new AsyncTask<Void, Void, List<TodoItem>>(){

                        @Override
                        protected List<TodoItem> doInBackground(Void... voids) {
                            return controller.getAllHighestPriority();
                        }
                    }.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.show_all:
                lastItemId = itemId;
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
                break;


        }
    }
}
