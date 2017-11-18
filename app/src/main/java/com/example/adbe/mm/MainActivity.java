package com.example.adbe.mm;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.adbe.mm.Item;
import de.adbe.mm.ItemManager;

public class MainActivity extends AppCompatActivity {

//<<<<<<< HEAD
    public ItemManager itemM = new ItemManager(this);
//=======
    // Request id for the add entry activity
    private static int ADD_ENTRY_REQUEST = 1;

//>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        itemM.addItem("Titel irgendwie", 100000000000L, 2323);
        itemM.addItem("Titel2 irgendwie", 1000000000000L, 23);
        itemM.addItem("Titel ie", 10000000000000L, 100);
        itemM.addItem("Titel3 irgendwie", 100000000000000L, 10000);
        itemM.addStoredItems();
        ArrayList<Item> items = itemM.getAllItems();
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            addView((ViewGroup) this.findViewById(R.id.list),
                    item.getTitle(), item.getDate(), item.getValue());

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNew();
            }
        });
    }

    public void openNew(){
        Intent intent = new Intent(this, AddEntry.class);
        startActivityForResult(intent, ADD_ENTRY_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADD_ENTRY_REQUEST) {
            if(resultCode == RESULT_OK){
                //addView((ViewGroup) this.findViewById(R.id.list), data.getStringExtra("title"), data.getStringExtra("date"), data.getDoubleExtra("value", 2.0));
                //TODO tore data to itemM
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    public static void addView(ViewGroup vg, String title, String date, double value) {
        ViewGroup le = (ViewGroup) LayoutInflater.from(vg.getContext()).inflate(R.layout.list_element, null);
        ((TextView)le.getChildAt(1)).setText(value+"");

        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(0)).setText(title);


        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(1)).setText(date);

        vg.addView(le);
    }

    public void addView(ViewGroup vg, String title, long date, int value){


        ViewGroup le = (ViewGroup) LayoutInflater.from(vg.getContext()).inflate(
                R.layout.list_element, null);
        
        int cent = value % 100;
        String centS = "" + cent;
        if(cent < 10){
            centS = "0"+centS;
        }
        ((TextView)le.getChildAt(1)).setText(value/100+","+centS);

        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(0)).setText(title);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(1)).setText(df.format(new Date(date)));

        vg.addView(le);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
