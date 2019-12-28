package com.example.localadmin.carfitbit;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static android.provider.SettingsSlicesContract.KEY_LOCATION;

public class Maps extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, View.OnClickListener {

    private static final String TAG = Maps.class.getSimpleName();
    GoogleMap map;
    Marker[] parking = new Marker[6];
    LayoutInflater inflater;
    RelativeLayout mapCard;
    TabLayout tabLayout;
    SupportMapFragment mapFragment;

    boolean isVisible = false;

    List<LatLng> PLatLong = new ArrayList<>();
    static final LatLng PLATLNG6 = new LatLng(38.901868, -77.041762);
    static final LatLng PLATLNG1 = new LatLng(38.9012776,-77.0483722);
    static final LatLng PLATLNG2 = new LatLng(38.8997287,-77.0455614);
    static final LatLng PLATLNG3 = new LatLng(38.9000982,-77.0455989);
    static final LatLng PLATLNG4 = new LatLng(38.9028229,-77.0506896);
    static final LatLng PLATLNG5 = new LatLng(38.9011359,-77.0467203);

    List<LatLng> TLatLong = new ArrayList<>();
    static final LatLng TLATLNG1 = new LatLng(38.903055, -77.037890);
    static final LatLng TLATLNG2 = new LatLng(38.900917, -77.056633);
    static final LatLng TLATLNG3 = new LatLng(38.913141, -77.037835);
    static final LatLng TLATLNG4 = new LatLng(38.898570, -77.032483);
    static final LatLng TLATLNG5 = new LatLng(38.912176, -77.065005);
    static final LatLng TLATLNG6 = new LatLng(38.912103, -77.009546);

    List<LatLng> FSLatLong = new ArrayList<>();
    static final LatLng FSLATLNG1 = new LatLng(38.901953, -77.056248);
    static final LatLng FSLATLNG2 = new LatLng(38.909767, -77.029293);
    static final LatLng FSLATLNG3 = new LatLng(38.915910, -77.016413);
    static final LatLng FSLATLNG4 = new LatLng(38.901270, -76.989378);
    static final LatLng FSLATLNG5 = new LatLng(38.897410, -77.029211);
    static final LatLng FSLATLNG6 = new LatLng(38.895270, -77.020629);

    List<LatLng> markerList = new ArrayList<>();

    static final LatLng KIEL = new LatLng(53.551, 9.993);
    Marker navMarker;

    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location mLastKnownLocation;
    Point displaySize;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);


        initMap();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        if(map!=null)
        {
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void initMap() {

        PLatLong.add(PLATLNG1);
        PLatLong.add(PLATLNG2);
        PLatLong.add(PLATLNG3);
        PLatLong.add(PLATLNG4);
        PLatLong.add(PLATLNG5);
        PLatLong.add(PLATLNG6);

        TLatLong.add(TLATLNG1);
        TLatLong.add(TLATLNG2);
        TLatLong.add(TLATLNG3);
        TLatLong.add(TLATLNG4);
        TLatLong.add(TLATLNG5);
        TLatLong.add(TLATLNG6);

        FSLatLong.add(FSLATLNG1);
        FSLatLong.add(FSLATLNG2);
        FSLatLong.add(FSLATLNG3);
        FSLatLong.add(FSLATLNG4);
        FSLatLong.add(FSLATLNG5);
        FSLatLong.add(FSLATLNG6);

        markerList=PLatLong;
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        mapCard = findViewById(R.id.maps_card_layout);
        mapCard.setOnClickListener(this);

        ImageButton nav = findViewById(R.id.nav);
        nav.setOnClickListener(this);


    }

    private void initTab() {

        tabLayout = findViewById(R.id.tabs);
        TabLayout.Tab parkingTab = tabLayout.newTab();
        parkingTab.setText("Parking");

        TabLayout.Tab towTab = tabLayout.newTab();
        towTab.setText("Tow");

        TabLayout.Tab fuelTab = tabLayout.newTab();
        fuelTab.setText("Gas Station");

        tabLayout.addTab(parkingTab);
        tabLayout.addTab(towTab);
        tabLayout.addTab(fuelTab);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getText()=="Tow")
                {
                    markerList=TLatLong;
                    LatLngBounds bounds = new LatLngBounds.Builder().include(markerList.get(0)).include(markerList.get(1))
                            .include(markerList.get(2)).include(markerList.get(3)).include(markerList.get(4)).include(markerList.get(5)).build();
                    map.clear();
                    for (int i = 0; i < markerList.size(); i++) {
                        parking[i] = map.addMarker(new MarkerOptions().position(markerList.get(i))
                                .title("Tow " + i));
                    }
                    map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x - 20, displaySize.y - 20, 50));
                }
                else if(tab.getText()=="Parking")
                {
                    markerList=PLatLong;
                    LatLngBounds bounds = new LatLngBounds.Builder().include(markerList.get(0)).include(markerList.get(1))
                            .include(markerList.get(2)).include(markerList.get(3)).include(markerList.get(4)).include(markerList.get(5)).build();
                    map.clear();
                    for (int i = 0; i < markerList.size(); i++) {
                        parking[i] = map.addMarker(new MarkerOptions().position(markerList.get(i))
                                .title("Parking " + i));
                    }
                    map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x - 20, displaySize.y - 20, 50));
                }
                else
                {
                    markerList=FSLatLong;
                    LatLngBounds bounds = new LatLngBounds.Builder().include(markerList.get(0)).include(markerList.get(1))
                            .include(markerList.get(2)).include(markerList.get(3)).include(markerList.get(4)).include(markerList.get(5)).build();
                    map.clear();
                    for (int i = 0; i < markerList.size(); i++) {
                        parking[i] = map.addMarker(new MarkerOptions().position(markerList.get(i))
                                .title("Gas Station " + i));
                        }
                    map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x-20, displaySize.y , 50));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;

        if (map!=null){
            for (int i = 0; i < markerList.size(); i++) {

                parking[i] = map.addMarker(new MarkerOptions().position(markerList.get(i))
                        .title("Parking " + i));
//            map.moveCamera(CameraUpdateFactory.newLatLng(PLATLNG));
//            map.getMaxZoomLevel();
            }

            map.setOnMarkerClickListener(this);

            getLocationPermission();

            updateLocationUI();

            getDeviceLocation();

            initTab();

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        for (int i = 0; i < parking.length ; i++) {
            if (marker.equals(parking[i])) {
                if (isVisible) {
                    mapCard.setVisibility(View.GONE);
                    isVisible = false;
                } else {
                    mapCard.setVisibility(View.VISIBLE);
                    isVisible = true;
                    navMarker = marker;
                }
            }
        }
        return false;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.nav:

                Uri gmmIntentUri = Uri.parse("google.navigation:q="+navMarker.getPosition().latitude + ","
                        + navMarker.getPosition().longitude );
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            case R.id.maps_card_layout:
                Intent intent = new Intent(this,InfoActivity.class);
                startActivity(intent);
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
                map.getUiSettings().setZoomControlsEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void getDeviceLocation() {

            try {
                if (mLocationPermissionGranted) {
                    Task locationResult = mFusedLocationProviderClient.getLastLocation();

                    locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful()) {
                                // Set the map's camera position to the current location of the device.
                                mLastKnownLocation =  task.getResult();
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                                map.getUiSettings().setMyLocationButtonEnabled(true);

                            } else {
                                Log.d(TAG, "Current location is null. Using defaults.");
                                Log.e(TAG, "Exception: %s", task.getException());
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                                map.getUiSettings().setMyLocationButtonEnabled(false);
                            }
                        }
                    });
                }
            } catch(SecurityException e)  {
                Log.e("Exception: %s", e.getMessage());
            }
        }
}
