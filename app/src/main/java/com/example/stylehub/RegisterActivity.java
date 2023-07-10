package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHandler dbHandler;

    EditText inputUsername, inputPassword, inputConfirmPassword, inputNama;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHandler = new DatabaseHandler(this);
        inputUsername = findViewById(R.id.username);
        inputPassword = findViewById(R.id.password);
        inputConfirmPassword = findViewById(R.id.confirmPassword);
        inputNama = findViewById(R.id.nama);
        btnRegister = findViewById(R.id.registerBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                String confirmPassword = inputConfirmPassword.getText().toString();
                String nama = inputNama.getText().toString();

                if (!password.equals(confirmPassword)){
                    Toast.makeText(RegisterActivity.this, "Password salah!", Toast.LENGTH_SHORT).show();
                } else{
                    if(dbHandler.getUser(username) != null){
                        Toast.makeText(RegisterActivity.this, "Username sudah digunakan!", Toast.LENGTH_SHORT).show();
                    } else{
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setNama(nama);
                        if(dbHandler.addRecordUserAccount(user) == -1){
                            Toast.makeText(RegisterActivity.this, "Registrasi gagal", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });

    }
}