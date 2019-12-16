package com.example.sqlitedatabaseapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.adapter.ReceiptAdapter;
import com.example.sqlitedatabaseapplication.database.ReceiptDatabaseHelper;
import com.example.sqlitedatabaseapplication.model.Receipt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ReceiptAdapter.ReceiptDelegate, ViewReceiptFragment.ReceiptFragmentListener {

    private ReceiptDatabaseHelper receiptDatabase;

    private List<Receipt> receiptList = new ArrayList<>();

    private ViewReceiptFragment receiptFragment = new ViewReceiptFragment();
    private ViewReceiptFragment receiptFragment2 = new ViewReceiptFragment();

    @BindView(R.id.receipt_recyclerview)
    RecyclerView receiptRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RecyclerView.ItemDecoration decorator = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        receiptRecyclerView.addItemDecoration(decorator);

        receiptDatabase = new ReceiptDatabaseHelper(this, "", null, 0);

        receiptDatabase.insertReceipt(new Receipt("The Battery Expense", "$500"));
        receiptDatabase.insertReceipt(new Receipt("The Battery Expense2", "$50"));
        receiptDatabase.insertReceipt(new Receipt("The Battery Expense3", "$80"));
        receiptDatabase.insertReceipt(new Receipt("The Battery Expense4", "$5"));
        receiptDatabase.insertReceipt(new Receipt("The Battery Expense5", "$10"));

        Cursor receipts = receiptDatabase.retrieveReceipts();
        receipts.moveToFirst();

        while (receipts.moveToNext()) {
            int receiptId = receipts.getInt(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_ID));
            String receiptTitle = receipts.getString(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_TITLE));
            String receiptPrice = receipts.getString(receipts.getColumnIndex(ReceiptDatabaseHelper.COLUMN_RECEIPT_PRICE));

            Receipt receipt = new Receipt(receiptId, receiptTitle, receiptPrice);
            receiptList.add(receipt);
            Log.d("TAG_X", receiptTitle + " " + receiptPrice);

            receiptRecyclerView.setAdapter(new ReceiptAdapter(receiptList, this));
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            receiptRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void deleteReceipt(Receipt receipt) {
//        TODO: delete receipt

    }

    @Override
    public void viewReceipt(Receipt receipt) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("my_key", receipt);
        receiptFragment.setArguments(bundle);
        receiptFragment2.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_framelayout, receiptFragment)
                .addToBackStack(receiptFragment.getTag())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_framelayout2, receiptFragment2)
                .addToBackStack(receiptFragment.getTag())
                .commit();

    }

    @Override
    public void makeToast(String price) {
        getSupportFragmentManager().popBackStack();
        Toast.makeText(this, "The price : " + price, Toast.LENGTH_SHORT).show();
    }
}
