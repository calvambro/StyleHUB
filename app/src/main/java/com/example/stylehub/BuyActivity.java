package com.example.stylehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListProductBuyAdapter adapter;
    ArrayList<Product> arrProduct;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");

        databaseHandler = new DatabaseHandler(this);

        SharedPreferences sharedPreferences = getSharedPreferences("product", MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("isInserted", false)){
            ArrayList<Product> listProduct = new ArrayList<>();
            listProduct.add(new Product("T-Shirt Hitam", 75000));
            listProduct.add(new Product("Jacket Jeans", 550000));
            listProduct.add(new Product("Hoodie Hijau", 250000));
            listProduct.add(new Product("Celana Joger", 185000));
            listProduct.add(new Product("Kaos Polos Biru", 85000));
            listProduct.add(new Product("Sarung Tangan Merah", 50000));

            for (int i = 0; i<listProduct.size(); i++){
                databaseHandler.addRecordProduct(listProduct.get(i));
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isInserted", true);
            editor.apply();
        }

        arrProduct = databaseHandler.getAllProduct();
//        arrProduct.add(product);

        int userId = databaseHandler.getUser(username).getId();
        adapter = new ListProductBuyAdapter(this, arrProduct, userId);
        recyclerView = findViewById(R.id.recyclerviewProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}