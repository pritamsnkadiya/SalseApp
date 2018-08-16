package com.example.pritam.fragments;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pritam.salseapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddNewMap extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private View rootView;
    private Button add_new_next, save_map;
    String TAG = AddNewMap.class.getName ();

    public AddNewMap() {
        // Required empty public constructor
    }

    public static AddNewMap newInstance(String param1, String param2) {
        AddNewMap fragment = new AddNewMap ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach (activity);
        Log.i (TAG, "onAttach");
    }

    @Override
    public void onResume() {
        super.onResume ();
    }

    @Override
    public void onPause() {
        super.onPause ();
        Log.i (TAG, "onPause");
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        Log.i (TAG, "onDetach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        Log.i (TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate (R.layout.fragment_add_new_map, container, false);
        //MapsInitializer.initialize (getContext ());
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager ().findFragmentById (R.id.map);
        mapFragment.onCreate (savedInstanceState);
        add_new_next = (Button) rootView.findViewById (R.id.add_new_next);
        save_map = (Button) rootView.findViewById (R.id.save_map);
        mapFragment.getMapAsync (this);

        save_map.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                add_new_next.setEnabled (true);
                add_new_next.setTextColor (Color.WHITE);
                add_new_next.setBackgroundColor (getResources ().getColor (R.color.colorPrimary));
                save_map.setVisibility (View.INVISIBLE);
            }
        });
        add_new_next.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                swapFragment ();
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView ();
    }

    private void swapFragment() {
        AddNew newAddNew = new AddNew ();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction ();
        fragmentTransaction.replace (R.id.frame_container, newAddNew);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission (getContext (),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission (getContext (),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions (getActivity (),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
        } else {
            Log.e ("DB", "PERMISSION Dennie");
        }
        Log.d (TAG, "OnmapReady");
        mMap.setMyLocationEnabled (true);
        mMap.setOnMyLocationButtonClickListener (this);
        mMap.setOnMyLocationClickListener (this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        //Toast.makeText(getActivity (), "Current location:\n" + location, Toast.LENGTH_LONG).show();
        locatioCall(location);
        Log.d (TAG, location + "");
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Log.d (TAG, "MyLocation button clicked");
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    public void locatioCall(Location location) {
        LatLng gurgaon = new LatLng (location.getLatitude (), location.getLongitude ());
        Marker melbourne = mMap.addMarker (new MarkerOptions ()
                .position (gurgaon)
                .title ("Info !")
                .snippet (location.getLatitude ()+" "+location.getLongitude ())
                .icon (BitmapDescriptorFactory.fromResource (R.drawable.marker_app)));
        mMap.moveCamera (CameraUpdateFactory.newLatLngZoom (
                new LatLng (location.getLatitude (), location.getLongitude ()), 15));
    }
}