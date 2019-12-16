package com.example.sqlitedatabaseapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.model.Receipt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewReceiptFragment extends Fragment {


    public interface ReceiptFragmentListener {
        void makeToast(String price);
    }

    private ReceiptFragmentListener fragmentListener;

    @BindView(R.id.receipt_textview)
    TextView receiptTitleTextView;

    @BindView(R.id.receipt_cost_textview)
    TextView receiptCostTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.receipt_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Receipt receipt = (Receipt) getArguments().getParcelable("my_key");

        if (receipt != null) {
            receiptTitleTextView.setText(receipt.getReceiptTitle());
            receiptCostTextView.setText(receipt.getReceiptPrice());

            receiptCostTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentListener.makeToast(receipt.getReceiptPrice());
                }
            });
        }

    }

    @OnClick(R.id.close_icon_imagevew)
    public void closeFragment(View view) {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("TAG_X", "Attach");

        if(context instanceof MainActivity)
        {
            this.fragmentListener = (MainActivity)context;
        }
    }
}
