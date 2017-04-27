package com.example.gulnara.graduatework;

/**
 * Created by gulnara on 4/25/17.
 */

public class User {
    String name;
    int id;
    int sum;

    public User(String n, int s, int i){
        this.name = n;
        this.sum = s;
        this.id = i;
    }

    public User(int i){
        this.id = i;
        this.name = "Гость "+id;
        this.sum = 0;
    }
}
