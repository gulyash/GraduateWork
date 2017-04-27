package com.example.gulnara.graduatework.billEditor;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.gulnara.graduatework.Dish;
import com.example.gulnara.graduatework.R;

/**
 * Created by gulnara on 4/16/17.
 */

public class NewDishDialogFragment extends DialogFragment {
    EditText nameEdit;
    EditText qtyEdit;
    EditText priceEdit;

    public interface NewDishDialogListener{
        public void onNewDishDialogPositiveClick(DialogFragment dialog, Dish newDish);
    }

    NewDishDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NewDishDialogFragment.NewDishDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement BillItemDialogListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.bill_editor_list_item_dialog_fragment, null);

        nameEdit = (EditText)v.findViewById(R.id.editName);
        qtyEdit = (EditText)v.findViewById(R.id.editQty);
        priceEdit = (EditText)v.findViewById(R.id.editPrice);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle("Новое блюдо:");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String n = (nameEdit.getText().length()==0) ? "Не указано" : nameEdit.getText().toString();
                int p = parseInt(priceEdit.getText().toString());
                int q = parseInt(qtyEdit.getText().toString());

                Dish newDish = new Dish(n, p, q);
                mListener.onNewDishDialogPositiveClick(NewDishDialogFragment.this, newDish);
            }
        });

        builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nothing
            }
        });
        return builder.create();
    }

    private int parseInt(String s){
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e) {
            return 1;
        }
    }
}
