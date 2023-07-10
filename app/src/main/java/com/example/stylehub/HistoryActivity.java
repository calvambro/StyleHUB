package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListHistoryAdapter adapter;
    ArrayList<Transaction> arrTransaction;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        databaseHandler = new DatabaseHandler(this);

        int userId = databaseHandler.getUser(username).getId();
        arrTransaction = databaseHandler.getTransaction(userId);

        adapter = new ListHistoryAdapter(this, arrTransaction, userId);
        recyclerView = findViewById(R.id.recyclerviewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}