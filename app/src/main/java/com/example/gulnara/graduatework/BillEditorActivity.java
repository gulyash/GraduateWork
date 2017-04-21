package com.example.gulnara.graduatework;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BillEditorActivity extends AppCompatActivity implements BillItemDialogFragment.BillItemDialogListener, NewDishDialogFragment.NewDishDialogListener{
    ArrayList<Dish> bill;
    BillEditorAdapter billEditorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_editor);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //TextView recognizedTextView = (TextView)findViewById(R.id.recognizedText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String recognized = extras.getString("recognized");
            BillParser billParser = new BillParser();
            bill = billParser.parse(recognized);
            //String result = "placeholder";
            //recognizedTextView.setText(result);
        }



        final ListView billList = (ListView)findViewById(R.id.list);
        billEditorAdapter = new BillEditorAdapter(this, bill);
        billList.setAdapter(billEditorAdapter);

        billList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogFragment dialogFragment = new BillItemDialogFragment();
                Bundle args = new Bundle();
                args.putInt("position", i);
                args.putString("name", bill.get(i).name);
                args.putInt("price", bill.get(i).price);
                args.putInt("quantity", bill.get(i).quantity);
                dialogFragment.setArguments(args);
                dialogFragment.show(getSupportFragmentManager(), "list item " + i + " dialog");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bill_editor_menu, menu);
        return true;
    }

    public void onNextButtonClick(MenuItem item) {
        Toast.makeText(this, "you clicked next button", Toast.LENGTH_SHORT).show();
    }

    public void onAddButtonClick(View view) {
        DialogFragment dialogFragment = new NewDishDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "new dish dialog");
    }

    @Override
    public void onBillItemDialogPositiveClick(DialogFragment dialog, Dish newDish, int i) {
        bill.set(i, newDish);
        billEditorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBillItemDialogNegativeClick(DialogFragment dialog, int i) {
        bill.remove(i);
        billEditorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewDishDialogPositiveClick(DialogFragment dialog, Dish newDish) {
        bill.add(newDish);
        billEditorAdapter.notifyDataSetChanged();
    }
}
