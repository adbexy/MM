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
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for(int i = 0; i < 3; i++) {
            addView((ViewGroup) this.findViewById(R.id.list), "Titel"+i, 1000000000L*i, 100*i);

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Okay", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openNew();
            }
        });
    }

    public void addView(ViewGroup vg, String title, long date, int value){


        ViewGroup le = (ViewGroup) LayoutInflater.from(vg.getContext()).inflate(
                R.layout.list_element, null);
        ((TextView)le.getChildAt(1)).setText(value+",00");

        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(0)).setText(title);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        ((TextView)((ViewGroup)le.getChildAt(0)).getChildAt(1)).setText(df.format(new Date(date)));

        vg.addView(le);

        /*LinearLayout ll = new LinearLayout(vg.getContext());
        setContentView(R.layout.list_element);

        vg.addView(ll);*/

        /*LinearLayout ll = new LinearLayout(vg.getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout subll = new LinearLayout(ll.getContext());
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView titleV = new TextView(subll.getContext());
        titleV.setText(title);


        TextView dateV = new TextView(subll.getContext());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dateV.setText(df.format(new Date(date)));

        TextView valueV = new TextView(ll.getContext());
        String cent = ""+value%100;
        if(cent.length() >= 9){
            cent = "0"+cent;
        }
        valueV.setText(value/100 + "," + cent);

        subll.addView(titleV);
        subll.addView(dateV);
        ll.addView(subll);
        ll.addView(valueV);
        vg.addView(ll);*/

    }

    public void openNew(){
        Intent intent = new Intent(this, AddEntry.class);
        startActivity(intent);
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
