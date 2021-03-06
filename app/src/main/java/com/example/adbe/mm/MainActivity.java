package com.example.adbe.mm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.adbe.mm.Item;
import de.adbe.mm.ItemManager;

public class MainActivity extends AppCompatActivity {

    public ItemManager itemM = new ItemManager(this);
    // Request id for the add entry activity
    private static int ADD_ENTRY_REQUEST = 1;
    public LinearLayout list;


    // request IDs for permissions
    public static final int PERMISSIONS_REQUEST_READ_STORAGE = 142;
    public static final int PERMISSIONS_REQUEST_WRITE_STORAGE = 143;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        list = (LinearLayout) findViewById(R.id.list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNew();
            }
        });

        //requestPermissions();
    }

    /**
     * Method to request certain Permissions
     */
    public void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_STORAGE);
        }
    }

    /**
     * Overriding existing method, waits for the result of certain permission requests
     * by: Vincent da best
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        finish(); // denied (close App)
                    } else {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            // granted (do nothing more)
                        } else {
                            finish(); // not clicked (close App)
                        }
                    }
                }
            } case PERMISSIONS_REQUEST_READ_STORAGE: {
                if (grantResults.length > 0) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        finish(); // denied (close App)
                    } else {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_WRITE_STORAGE);
                            }
                        } else {
                            finish(); // not clicked (close App)
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        list.removeAllViews();
        showListItems(itemM);
    }

    private void showListItems(ItemManager im){
        ArrayList<Item> items = im.getAllItems();
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            addView((ViewGroup) this.findViewById(R.id.list),
                    item.getTitle(), item.getDate(), item.getValue());
        }
    }

    public void openNew(){
        Intent intent = new Intent(this, AddEntry.class);
        startActivityForResult(intent, ADD_ENTRY_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADD_ENTRY_REQUEST) {
            if(resultCode == RESULT_OK){
                //addView((ViewGroup) this.findViewById(R.id.list), data.getStringExtra("title"), data.getStringExtra("date"), data.getDoubleExtra("value", 2.0));
                //TODO store data to item
                itemM.addItem(data.getStringExtra("title"), data.getLongExtra("date", 0L),
                        data.getIntExtra("value", 0));
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

    public void addView(ViewGroup vg, String title, long date, int value){

        ViewGroup le = (ViewGroup) LayoutInflater.from(vg.getContext()).inflate(
                R.layout.list_element, null);

        String valueS = "";
        if(value < 0) {
            valueS += "-";
            value = Math.abs(value);
        }
        valueS += value/100 + ".";
        if(value % 100 < 10) valueS += "0";
        valueS += "" + value % 100;

        if(valueS.charAt(0) == '-'){
            ((TextView)le.getChildAt(1)).setTextColor(getResources().getColor(R.color.valueRed, null));
        }
        ((TextView)le.getChildAt(1)).setText(valueS);

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
