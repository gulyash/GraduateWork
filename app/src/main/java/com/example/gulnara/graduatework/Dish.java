package com.example.gulnara.graduatework;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by gulnara on 4/8/17.
 */

public class Dish implements Parcelable {
    public String name;
    //WARNING! dish price is stored in cents.
    public int price;
    public int quantity;

    public Dish(String n, int p, int qty){
        this.name = n;
        this.price = p;
        this.quantity = qty;
    }

    protected Dish(Parcel in) {
        name = in.readString();
        price = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(quantity);
    }
}
