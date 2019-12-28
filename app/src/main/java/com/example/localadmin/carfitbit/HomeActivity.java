package com.example.localadmin.carfitbit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton fuel;
    ImageButton tyre;
    ImageButton service;
    ImageButton locate;
    ImageButton tow;
    ImageButton sos;
    ImageButton siren;
    ImageButton lock;
    ImageButton boot;
    int count = 0;
    Intent mapsInent;
    boolean lockStatus=true;
    boolean ringing=false;
    boolean trunkClose=true;
    RotateAnimation rotateAnimation;
    MediaPlayer trunkLockSound;
    MediaPlayer trunkUnlockSound;
    MediaPlayer carLockSound;
    MediaPlayer sosSound;
    MediaPlayer ringerSound;
    boolean soundStart=false;
    Intent callIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapsInent = new Intent(this,Maps.class);

        String phone = "+14253622832";
        callIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));


        fuel = findViewById(R.id.fuel);
        tow = findViewById(R.id.tow);
        sos = findViewById(R.id.sos);
        service = findViewById(R.id.service);
        tyre = findViewById(R.id.pressure);
        locate = findViewById(R.id.locate);
        lock = findViewById(R.id.lock);
        siren = findViewById(R.id.siren);
        boot = findViewById(R.id.boot);

        fuel.setOnClickListener(this);
        tow.setOnClickListener(this);
        sos.setOnClickListener(this);
        service.setOnClickListener(this);
        tyre.setOnClickListener(this);
        lock.setOnClickListener(this);
        locate.setOnClickListener(this);
        siren.setOnClickListener(this);
        boot.setOnClickListener(this);

        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);


        trunkLockSound = MediaPlayer.create(getApplicationContext(), R.raw.car_trunk_lock);
        trunkUnlockSound = MediaPlayer.create(getApplicationContext(), R.raw.car_trunk_unlock);
        carLockSound = MediaPlayer.create(getApplicationContext(), R.raw.beep_parking);
        sosSound = MediaPlayer.create(getApplicationContext(), R.raw.sos_mobile);
        ringerSound = MediaPlayer.create(getApplicationContext(), R.raw.ringer_sound);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fuel: {
                Intent intent = new Intent(this, Maps.class);
                startActivity(intent);
                break;
            }

            case R.id.tow: {
                Intent intent = new Intent(this, Maps.class);
                startActivity(intent);
                break;
            }

            case R.id.sos: {
                count++;
                if (count < 3) {
                    Toast.makeText(this, "SOS CALL! Press again " + (3 - count) + " times", Toast.LENGTH_SHORT).show();
                    if(soundStart) {
                        sosSound.stop();
                        soundStart=false;
                    }
                } else {
                    Toast.makeText(this, "MAKING SOS CALL! ", Toast.LENGTH_LONG).show();

                    if(!soundStart) {
                        sosSound.start();
                        soundStart=true;
                    }
                    count = 0;
                }
                break;
            }

            case R.id.service: {
                Intent intent = new Intent(this, ServiceActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.pressure: {
                Intent intent = new Intent(this, TyreActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.locate: {
                Intent intent = new Intent(this, LocateActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.lock: {

                if(lockStatus) {
                    lock.setImageResource(R.drawable.unlock_icon_100);
                    lockStatus= false;
                    carLockSound.start();
                }
                else
                {
                    lock.setImageResource(R.drawable.lock_icon_100);
                    lockStatus= true;
                    carLockSound.start();
                }
                break;
            }

            case R.id.siren: {

                if(!ringing) {
                    siren.startAnimation(rotateAnimation);
                    ringerSound.start();
                    ringing=true;
                }
                else
                {
                    siren.clearAnimation();
                    ringerSound.stop();
                    ringing=false;
                }
                break;
            }

            case R.id.boot: {

                if(trunkClose) {
                    boot.setImageResource(R.drawable.trunk_open);
                    trunkClose= false;
                    trunkUnlockSound.start();
                }
                else
                {
                    boot.setImageResource(R.drawable.trunk_close);
                    trunkClose= true;
                    trunkLockSound.start();
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.parking_menu) {

                    startActivity(mapsInent);
                    return true;
                }

                if (item.getItemId() == R.id.help_menu) {

                    startActivity(callIntent);
                    return true;
                }

                return true;


            }
        });
        return super.onOptionsItemSelected(item);
    }
}
