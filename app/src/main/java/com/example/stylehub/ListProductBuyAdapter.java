package com.example.stylehub;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListProductBuyAdapter extends RecyclerView.Adapter<ListProductBuyAdapter.ListViewHolder> {
    private ArrayList<Product> listProductBuy;
    private LayoutInflater inflater;
    private DatabaseHandler databaseHandler;
    private int userId;

    public ListProductBuyAdapter(Context context, ArrayList<Product> list, int userId) {
        inflater = LayoutInflater.from(context);
        this.listProductBuy = list;
        this.userId = userId;
        databaseHandler = new DatabaseHandler(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_product_buy, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Product product = listProductBuy.get(position);

        holder.namaBarangView.setText(product.getName());
        String priceStr = "Rp" + Integer.toString(product.getPrice());
        holder.priceView.setText(priceStr);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Transaction transaction = new Transaction();
                transaction.setIdProduct(product.getIdProduct());
                transaction.setIdUser(userId);
                transaction.setTransactionDate(LocalDate.now().toString());
                databaseHandler.addRecordTransaction(transaction);
                Toast.makeText(inflater.getContext(), "Pembelian berhasil", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProductBuy.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView namaBarangView, priceView;

        public ListViewHolder(View view) {
            super(view);
            namaBarangView = view.findViewById(R.id.nama_barang);
            priceView = view.findViewById(R.id.price);
        }
    }
}
