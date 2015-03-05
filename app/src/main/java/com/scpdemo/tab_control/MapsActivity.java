package com.scpdemo.tab_control;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import com.scpdemo.DisiAndroid.R;


public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback{

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragMap);
        supportMapFragment.getMapAsync(MapsActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.clear();
        //-12.087154, -77.049477
        LatLng latLng = new LatLng(-11.990432, -77.063462);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Restobar RCA");
        markerOptions.flat(true);
        markerOptions.snippet("Carlos Alberto Izaguirre 295\n" +
                "Lima 15311");
        googleMap.addMarker(markerOptions);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17), 1500, null);

//          PolylineOptions polylineOptions = new PolylineOptions();
//          polylineOptions.add(new LatLng(-12.086304, -77.049595));
        //         polylineOptions.add(new LatLng(-12.085365, -77.048779));
        //         polylineOptions.add(new LatLng(-12.085497, -77.048543));
        //        polylineOptions.add(new LatLng(-12.088188, -77.048168));
//          polylineOptions.add(new LatLng(-12.088355, -77.049294));

//         googleMap.addPolyline(polylineOptions);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-11.990019, -77.063097));
        polygonOptions.add(new LatLng(-11.990389, -77.063050));
        polygonOptions.add(new LatLng(-11.990432, -77.063462));
        polygonOptions.add(new LatLng(-11.990162, -77.063349));
        polygonOptions.add(new LatLng(-11.990019, -77.063097));

        polygonOptions.fillColor(0x1100FF00);

        googleMap.addPolygon(polygonOptions);



        //   CircleOptions circleOptions = new CircleOptions();
        //   circleOptions.center(new LatLng(-12.087354, -77.049782));
        //   circleOptions.radius(10);
        //  googleMap.addCircle(circleOptions);
    }
}
