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
//<<<<<<< HEAD
//=======
import android.widget.DatePicker;
//>>>>>>> origin/master
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.adbe.mm.ItemManager;

public class AddEntry extends AppCompatActivity implements DatePickerFragment.OnDateSetListener {

    private Date date;

    //dp != Double Penetration
    private DialogFragment dp;
    private TextView datePreview;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    //et != E.T.
    private EditText eTitle;
    private EditText eValue;

    private CheckBox stime;
    private CheckBox income;
//>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        stime = (CheckBox) findViewById(R.id.useSystemTime);
        stime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.changeDate).setEnabled(false);
                    datePreview.setText(sdf.format(Calendar.getInstance().getTime()));
                }
                else {
                    findViewById(R.id.changeDate).setEnabled(true);
                    datePreview.setText(sdf.format(date));
                }
        }
        });


        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creates an Intent storing the necessary data
                Intent returnInt = new Intent();
                if(stime.isChecked()){
                    returnInt.putExtra("date", Calendar.getInstance().getTime().getTime());
                }
                else {
                    returnInt.putExtra("date", date.getTime());
                }
                returnInt.putExtra("title", eTitle.getText().toString());
                returnInt.putExtra("value",
                        parseValue(eValue.getText().toString(), !income.isChecked()));
                setResult(RESULT_OK, returnInt);

                // closes the Activity
                finish();
            }
        });

        eTitle = (EditText) findViewById(R.id.editTitle);
        eValue = (EditText) findViewById(R.id.editValue);
        income = (CheckBox) findViewById(R.id.income);
        datePreview = (TextView) findViewById(R.id.datePreview);

        Calendar c = Calendar.getInstance();
        date = c.getTime();
        datePreview.setText(sdf.format(date));
    }

    public void showDatePickerDialog(View v) {
        dp = new DatePickerFragment();
        dp.show(getSupportFragmentManager(), "datePicker");
    }

    /*public void saveItem(View view){
        String title = ((EditText)findViewById(R.id.editTitle)).getText().toString();

        String dateS = ((String) ((TextView)findViewById(R.id.datePreview)).getText());
        long date = parseDate(dateS);

        String valueS = ((EditText)findViewById(R.id.editValue)).getText().toString();
        int value = parseValue(valueS);

        System.out.println(getCallingActivity());
        ItemManager.storeItem(title, date, value);


    }*/

    private int parseValue(String valueS, boolean negative){
        int value = 0;
        int indexOfSeperator = valueS.indexOf( (int)('.') );

        if(indexOfSeperator > -1){
            String storage = valueS.substring(0, indexOfSeperator);
            if(!storage.equals("")){
                value += Integer.parseInt(storage)*100;
            }

            if(valueS.length() == indexOfSeperator) storage = "0";
            else if (valueS.length() < indexOfSeperator+3) storage = valueS.substring(indexOfSeperator + 1);
            else storage = valueS.substring(indexOfSeperator + 1, indexOfSeperator + 3);

            while (storage.length() < 2){
                storage += "0";
            }

            value += Integer.parseInt(storage);
        }
        else {
            if(!valueS.equals("")) {
                value = Integer.parseInt(valueS) * 100;
            }
        }

        if (negative) return -1 * value;
        else return value;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        date = calendar.getTime();
        datePreview.setText(sdf.format(date));
    }
}
