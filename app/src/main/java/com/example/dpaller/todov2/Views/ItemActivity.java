package com.example.dpaller.todov2.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.dpaller.todov2.Controllers.HomeController;
import com.example.dpaller.todov2.Models.TodoItem;
import com.example.dpaller.todov2.R;


public class ItemActivity extends AppCompatActivity {

    private HomeController _controller;

    // TextEdits
    private EditText _nameField;
    private EditText _priorityField;
    private EditText _descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        _controller = new HomeController(getApplicationContext());

    }

    public void AddItemToDatabaseAndRedirect(View view){
        _nameField = (EditText)findViewById(R.id.addName);
        _descriptionField = (EditText)findViewById(R.id.addNotes);
        _priorityField = (EditText)findViewById(R.id.addPriority);

        // Add into db
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                _controller.addItem(
                        new TodoItem(
                                _nameField.getText().toString(),
                                "",
                                Integer.parseInt(_priorityField.getText().toString()),
                                _descriptionField.getText().toString(),
                                false
                        )
                );
                return 1;
            }
        }.execute();

        // redirect to home
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
