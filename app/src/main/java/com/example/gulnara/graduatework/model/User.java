package com.example.gulnara.graduatework.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gulnara on 4/25/17.
 */

public class User implements Parcelable{
    public String name;
    public int id;
    public int sum;

    public User(String n, int s, int i){
        this.name = n;
        this.sum = s;
        this.id = i;
    }

    public User(int i){
        this.id = i;
        this.name = "Гость " + (id+1);
        this.sum = 0;
    }

    protected User(Parcel in) {
        name = in.readString();
        id = in.readInt();
        sum = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(sum);
    }
}
