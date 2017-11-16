package com.example.adbe.mm;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.picker.adbe.mm.DatePickerFragment;

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
}
