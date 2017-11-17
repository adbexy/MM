package com.example.adbe.mm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEntry extends AppCompatActivity implements DatePickerFragment.OnDateSetListener {

    private Date date;

    //dp != Double Penetration
    private DialogFragment dp;
    private TextView tv;
    //et != E.T.
    private EditText et1;
    private EditText et2;

    private CheckBox stime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        stime = (CheckBox) findViewById(R.id.useSystemTime);
        stime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {findViewById(R.id.changeDate).setEnabled(false);}
                else {findViewById(R.id.changeDate).setEnabled(true);}
            }
        });

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                // creates an Intent storing the necessary data
                Intent returnInt = new Intent();
                returnInt.putExtra("date", sdf.format(date));
                returnInt.putExtra("title", et1.getText().toString());
                returnInt.putExtra("value", 1.0);
                setResult(RESULT_OK, returnInt);

                // closes the Activity
                finish();
            }
        });

        et1 = (EditText) findViewById(R.id.editTitle);
        et2 = (EditText) findViewById(R.id.editValue);
        tv = (TextView) findViewById(R.id.datePreview);
    }

    public void showDatePickerDialog(View v) {
        dp = new DatePickerFragment();
        dp.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        tv.setText(sdf.format(date));
    }
}
