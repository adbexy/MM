package com.example.adbe.mm;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.picker.adbe.mm.DatePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.adbe.mm.ItemManager;

public class AddEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        CheckBox stime = (CheckBox) findViewById(R.id.useSystemTime);
        stime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {findViewById(R.id.changeDate).setEnabled(false);}
                else {findViewById(R.id.changeDate).setEnabled(true);}
        }
        });
    }

    public void setDatePreview(String text){
        ((TextView) findViewById(R.id.datePreview)).setText(text);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void saveItem(View view){
        String title = ((EditText)findViewById(R.id.editTitle)).getText().toString();

        String dateS = ((String) ((TextView)findViewById(R.id.datePreview)).getText());
        long date = parseDate(dateS);

        String valueS = ((EditText)findViewById(R.id.editValue)).getText().toString();
        int value = parseValue(valueS);

        System.out.println(getCallingActivity());
        ItemManager.storeItem(title, date, value);


    }

    private int parseValue(String valueS){
        int value;
        int indexOfSeperator = valueS.indexOf( (int)('.') );

        if(indexOfSeperator > -1){
            String storage = valueS.substring(0, indexOfSeperator);
            value = Integer.parseInt(storage)*100;

            storage = valueS.substring(indexOfSeperator+1, indexOfSeperator+2);
            value += Integer.parseInt(storage);
        }
        else {
            value = Integer.parseInt(valueS);
        }

        return value;
    }

    private long parseDate(String dateS){
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(dateS.substring(6)), Integer.parseInt(dateS.substring(3, 5)),
                Integer.parseInt(dateS.substring(0, 2)));
        return c.getTime().getTime();
    }
}
