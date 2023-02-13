package com.radefffactory.realtimelocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IBaseGpsListener {

    private static final int PERMISSION_LOCATION = 1000;

    TextView tv_raw, tv_formatted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_raw = findViewById(R.id.tv_raw);
        tv_formatted = findViewById(R.id.tv_formatted);

        //check for location permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
        } else {
            showLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showLocation();
            } else {
                Toast.makeText(this, "Location permission not granted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void showLocation() {
        tv_raw.setText("loading...");
        tv_formatted.setText("loading...");

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGpsStatusChanged(int event) {
        //nothing
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            tv_raw.setText("Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());
            tv_formatted.setText(LocationConverter.getLocationInDegrees(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //nothing
    }

    @Override
    public void onProviderEnabled(String provider) {
        //nothing
    }

    @Override
    public void onProviderDisabled(String provider) {
        //nothing
    }
}