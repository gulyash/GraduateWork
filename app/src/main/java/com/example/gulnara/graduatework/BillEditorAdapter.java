package com.example.gulnara.graduatework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gulnara on 3/24/17.
 */

public class BillEditorAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Dish> bill;

    BillEditorAdapter(Context context, ArrayList<Dish> b){
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        bill = b;
    }
    @Override
    public int getCount() {
        return bill.size();
    }

    @Override
    public Object getItem(int i) {
        return bill.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.bill_editor_list_item, viewGroup, false);
        }

        Dish dish = bill.get(i);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) v.findViewById(R.id.dish_name)).setText(dish.name);
        ((TextView) v.findViewById(R.id.dish_price)).setText(dish.price + " р.");
        ((TextView) v.findViewById(R.id.dish_quantity)).setText("x" + dish.quantity);
        return v;
    }
}
