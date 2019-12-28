package com.example.localadmin.carfitbit;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.example.localadmin.carfitbit.Models.OrderStatus;
import com.example.localadmin.carfitbit.Models.TimeLineModel;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ServiceAdapter serviceAdapter;
    ArrayList<TimeLineModel> serviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final Intent intent = new Intent(this,AddServiceActivity.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(intent,100);
            }
        });


        initRecyclerView();
        setDataListItems();
    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        serviceAdapter = new ServiceAdapter(serviceList, this);
        recyclerView.setAdapter(serviceAdapter);
    }

    private void setDataListItems() {

        OrderStatus status=OrderStatus.ACTIVE;
        serviceList.add(new TimeLineModel("First Service at Comapany's (14th and N)", "2017-02-12 08:00", status));
        serviceList.add(new TimeLineModel("Second Service (14th and N)", "2017-02-12 08:00", status));
        serviceList.add(new TimeLineModel("Lights Changed (Delayed) (14th and N)", "2017-02-11 21:00", status));
        serviceList.add(new TimeLineModel("New Air Filter! (25th Street)", "2017-02-11 18:00", status));
        serviceList.add(new TimeLineModel("Basic Service (25th Street)", "2017-02-11 09:30", status));
        serviceList.add(new TimeLineModel("First Service after Moving to Seattle (Areo Rd.)", "2017-02-11 08:00", status));
        serviceList.add(new TimeLineModel("Front Tyres Changed (Delayed) (Areo Rd.)", "2017-02-10 15:00", status));
        serviceList.add(new TimeLineModel("Accidental Repairs (Wisconsin Ave.) ", "2017-02-10 14:30", status));
        serviceList.add(new TimeLineModel("Routine Service", "2017-02-10 14:00", status));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {

            if (resultCode == RESULT_OK) {

                String dateTime= data.getStringExtra("dateTime");
                String notes= data.getStringExtra("notes");
                Log.d("NOTES VALUE:", notes);
                serviceList.add(new TimeLineModel(notes, dateTime, OrderStatus.ACTIVE));
                serviceAdapter.notifyDataSetChanged();
            }
        }
    }
}
