package com.example.sqlitedatabaseapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Receipt implements Parcelable {

    private int receiptID;
    private String receiptTitle;
    private String receiptPrice;

    public Receipt(String receiptTitle, String receiptPrice){
        this.receiptTitle = receiptTitle;
        this.receiptPrice = receiptPrice;
    }

    public Receipt(int receiptID, String receiptTitle, String receiptPrice){
        this.receiptID = receiptID;
        this.receiptPrice = receiptPrice;
        this.receiptTitle = receiptTitle;
    }

    protected Receipt(Parcel in) {
        receiptID = in.readInt();
        receiptTitle = in.readString();
        receiptPrice = in.readString();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(String receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public String getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(String receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(receiptID);
        dest.writeString(receiptTitle);
        dest.writeString(receiptPrice);
    }
}
