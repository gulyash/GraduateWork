package com.example.gulnara.graduatework.billEditor;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.R;

/**
 * Created by gulnara on 4/13/17.
 */

public class BillItemDialogFragment extends DialogFragment {
    EditText nameEdit;
    EditText qtyEdit;
    EditText priceEdit;

    int position;
    String name;
    int price;
    int quantity;

    public interface BillItemDialogListener{
        public void onBillItemDialogPositiveClick(DialogFragment dialog, Dish newDish, int position);
        public void onBillItemDialogNegativeClick(DialogFragment dialog, int position);
    }

    BillItemDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (BillItemDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement BillItemDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        position = getArguments().getInt("position");
        name = getArguments().getString("name");
        price = getArguments().getInt("price");
        quantity = getArguments().getInt("quantity");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.bill_editor_list_item_dialog_fragment, null);

        nameEdit = (EditText)v.findViewById(R.id.editName);
        qtyEdit = (EditText)v.findViewById(R.id.editQty);
        priceEdit = (EditText)v.findViewById(R.id.editPrice);

        nameEdit.setText(name);
        qtyEdit.setText("" + quantity);
        priceEdit.setText("" + price);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle("Блюдо:");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String n = (nameEdit.getText().length()==0) ? "Не указано" : nameEdit.getText().toString();
                int p = parseInt(priceEdit.getText().toString());
                int q = parseInt(qtyEdit.getText().toString());

                mListener.onBillItemDialogPositiveClick(BillItemDialogFragment.this, new Dish(n, p, q), position);
            }
        });

        builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nothing
            }
        });
        builder.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onBillItemDialogNegativeClick(BillItemDialogFragment.this, position);
            }
        });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private int parseInt(String s){
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e) {
            return 1;
        }
    }
}
