package com.example.localadmin.carfitbit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddServiceActivity extends AppCompatActivity {

    String dateTime;
    String notes;
    Button addService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add New Service Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        final Calendar myCalendar = Calendar.getInstance();
        final Intent data =getIntent();

        final EditText datePicker= findViewById(R.id.date_picker);
        final EditText timePicker= findViewById(R.id.time_picker);
        final EditText notesEdit= findViewById(R.id.notes_edit);
        addService=findViewById(R.id.add_service_button);


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddServiceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM-dd-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        datePicker.setText(sdf.format(myCalendar.getTime()));
                        String myFormat1 = "yyyy-MM-dd HH:mm"; //In which you need put here
                        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                        dateTime=sdf1.format(myCalendar.getTime());                    }
                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new TimePickerDialog(AddServiceActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        myCalendar.set(Calendar.MINUTE, minute);
                        String myFormat = "HH:mm:ss"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        timePicker.setText(sdf.format(myCalendar.getTime()));

                        String myFormat1 = "yyyy-MM-dd HH:mm"; //In which you need put here
                        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                        dateTime=sdf1.format(myCalendar.getTime());
                    }
                }, myCalendar
                        .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(timePicker.getText().toString().equals("")|| datePicker.getText().toString().equals("")||notesEdit.getText().toString().equals(""))
                {
                    Toast.makeText(v.getContext(),"Select Date and Time",Toast.LENGTH_LONG).show();
                }
                else
                {
                    notes=notesEdit.getText().toString();
                    data.putExtra("dateTime",dateTime);
                        data.putExtra("notes",notes);
                        setResult(RESULT_OK,data);
                        finish();
                }
            }
        });
    }
}
