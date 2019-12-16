package com.example.sqlitedatabaseapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.model.Receipt;

import org.w3c.dom.Text;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    private List<Receipt> receiptList;
    private ReceiptDelegate receiptDelegate;


    public interface ReceiptDelegate {
        void deleteReceipt(Receipt receipt);

        void viewReceipt(Receipt receipt);
    }


    public ReceiptAdapter(List<Receipt> receiptList, ReceiptDelegate receiptDelegate) {
        this.receiptList = receiptList;
        this.receiptDelegate = receiptDelegate;
    }

    @NonNull
    @Override
    public ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_item_layout, parent, false);

        return new ReceiptViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptViewHolder holder, final int position) {

        holder.receiptTitleTextView.setText(receiptList.get(position).getReceiptTitle());
        holder.receiptPriceTextView.setText(receiptList.get(position).getReceiptPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiptDelegate.viewReceipt(receiptList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }

    class ReceiptViewHolder extends RecyclerView.ViewHolder {

        TextView receiptTitleTextView;
        TextView receiptPriceTextView;

        public ReceiptViewHolder(@NonNull View itemView) {
            super(itemView);
            receiptTitleTextView = itemView.findViewById(R.id.title_textview);
            receiptPriceTextView = itemView.findViewById(R.id.receipt_textview);
        }
    }
}
