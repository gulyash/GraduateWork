package com.example.gulnara.graduatework.billEditor;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
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

import com.example.gulnara.graduatework.model.Dish;
import com.example.gulnara.graduatework.billSplitting.GuestChoiceActivity;
import com.example.gulnara.graduatework.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BillEditorActivity extends AppCompatActivity implements BillItemDialogFragment.BillItemDialogListener, NewDishDialogFragment.NewDishDialogListener{
    int guestNum;
    ArrayList<Dish> bill;
    BillEditorAdapter billEditorAdapter;
    TextView billSumText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_editor);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Редактор чека");
        setSupportActionBar(myToolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            guestNum = extras.getInt("guest num");
            String recognized = extras.getString("recognized");
            BillParser billParser = new BillParser();
            bill = billParser.parse(recognized);
        }

        final ListView billList = (ListView)findViewById(R.id.list);
        billEditorAdapter = new BillEditorAdapter(this, bill);
        billList.setAdapter(billEditorAdapter);

        billSumText = (TextView) findViewById(R.id.sumText);
        billSumText.setText("Сумма: " + billSum() + "р.");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bill_editor_menu, menu);
        return true;
    }

    public void onNextButtonClick(MenuItem item) {
        Intent intent = new Intent(this, GuestChoiceActivity.class);
        intent.putExtra("guest num", guestNum);
        intent.putParcelableArrayListExtra("bill", bill);
        startActivity(intent);
    }

    public void onAddButtonClick(View view) {
        DialogFragment dialogFragment = new NewDishDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "new dish dialog");
    }

    @Override
    public void onBillItemDialogPositiveClick(DialogFragment dialog, Dish newDish, int i) {
        bill.set(i, newDish);
        billEditorAdapter.notifyDataSetChanged();
        billSumText.setText("Сумма: " + billSum() + "р.");
    }

    @Override
    public void onBillItemDialogNegativeClick(DialogFragment dialog, int i) {
        bill.remove(i);
        billEditorAdapter.notifyDataSetChanged();
        billSumText.setText("Сумма: " + billSum() + "р.");
    }

    @Override
    public void onNewDishDialogPositiveClick(DialogFragment dialog, Dish newDish) {
        bill.add(newDish);
        billEditorAdapter.notifyDataSetChanged();
        billSumText.setText("Сумма: " + billSum() + "р.");
    }

    private int billSum() {
        int sum = 0;
        for (int i=0; i<bill.size(); i++) {
            sum+=bill.get(i).price*bill.get(i).quantity;
        }
        return  sum;
    }
}
