package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    Button changePasswordBtn, deleteBtn;
    TextView usernameView, nameView;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        changePasswordBtn = findViewById(R.id.change_pass_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        usernameView = findViewById(R.id.username_view);
        nameView = findViewById(R.id.name_view);

        databaseHandler = new DatabaseHandler(this);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        User user = databaseHandler.getUser(username);
        usernameView.setText("Username : "+ user.getUsername());
        nameView.setText("Nama : " + user.getNama());

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHandler.deleteUser(user.getId());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}