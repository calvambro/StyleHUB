package com.example.stylehub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListHistoryAdapter extends RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder> {
    private ArrayList<Transaction> listHistory;
    private LayoutInflater inflater;
    private DatabaseHandler databaseHandler;
    private int userId;

    public ListHistoryAdapter(Context context, ArrayList<Transaction> list, int userId) {
        inflater = LayoutInflater.from(context);
        this.listHistory = list;
        this.userId = userId;
        databaseHandler = new DatabaseHandler(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_history, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Transaction transaction = listHistory.get(position);

        Product product = databaseHandler.getProduct(transaction.getIdProduct());

        holder.namaBarangView.setText(product.getName());
        String priceStr = "Rp" + Integer.toString(product.getPrice());
        holder.priceView.setText(priceStr);
        holder.trxDate.setText(transaction.getTransactionDate());

    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView namaBarangView, priceView, trxDate;

        public ListViewHolder(View view) {
            super(view);
            namaBarangView = view.findViewById(R.id.nama_barang);
            priceView = view.findViewById(R.id.price);
            trxDate = view.findViewById(R.id.tanggal_trx);
        }
    }
}
