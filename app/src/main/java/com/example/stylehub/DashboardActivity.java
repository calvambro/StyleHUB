package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        LinearLayout buyBtn = findViewById(R.id.pembelianBtn);
        LinearLayout location = findViewById(R.id.lokasiBtn);
        LinearLayout historyBtn = findViewById(R.id.riwayatBtn);
        LinearLayout productBtn = findViewById(R.id.produkBtn);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Under Maintenance...", Toast.LENGTH_SHORT).show();
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        productBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}