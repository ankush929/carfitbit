package com.example.localadmin.carfitbit;

import android.graphics.Point;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

public class LocateActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Point displaySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_locate);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng car = new LatLng(38.898240, -77.052291);
        mMap.addMarker(new MarkerOptions().position(car).title("Your Car").icon(BitmapDescriptorFactory.fromResource(R.drawable.car_finder))).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(car, 15));}
}
