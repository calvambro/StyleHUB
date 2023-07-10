package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView oldPassword, newPassword, confirmNewPassword;
    Button changePasswordBtn;
    DatabaseHandler databaseHandler;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        databaseHandler = new DatabaseHandler(this);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        user = databaseHandler.getUser(username);

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmNewPassword = findViewById(R.id.confirmNewPassword);
        changePasswordBtn = findViewById(R.id.changePasswordBtn);

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = oldPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                String confNewPass = confirmNewPassword.getText().toString();

                if (!oldPass.equals(user.getPassword())){
                    Toast.makeText(ChangePasswordActivity.this, "Old Password Salah!", Toast.LENGTH_SHORT).show();
                } else{
                    if (!newPass.equals(confNewPass)){
                        Toast.makeText(ChangePasswordActivity.this, "Confirm Password Salah!", Toast.LENGTH_SHORT).show();
                    } else{
                        Boolean result = databaseHandler.updatePassword(user.getId(), newPass);
                        if (result){
                            Toast.makeText(ChangePasswordActivity.this, "Password Changed!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(ChangePasswordActivity.this, "Change Password Failed!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });

    }
}