package com.example.localadmin.carfitbit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TyreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyre_actiity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageView carImage= findViewById(R.id.carImage);
        ImageView carImage1= findViewById(R.id.carImage1);

        Glide.with(this)
                .load(R.drawable.car_gif).apply(new RequestOptions().fitCenter())
                .into(carImage);

        Glide.with(this)
                .load(R.drawable.car_gif_2).apply(new RequestOptions().fitCenter())
                .into(carImage1);

    }
}
