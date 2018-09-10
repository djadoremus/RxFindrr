package com.dps.rxfindrr_user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

public class HomeActivity extends FragmentActivity implements OnMapReadyCallback {

    private String TAG = "HOMEACTIVITY";
    private GoogleMap mMap;
    private FloatingActionButton fabCamera;
    private FloatingActionButton fabList;
    private FloatingActionsMenu fam;

    private Integer REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fam = findViewById(R.id.id_fam_home);
        fabCamera = findViewById(R.id.id_fab_camera);
        fabList = findViewById(R.id.id_fab_list);


        fam.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                Log.d(TAG, "--expanded");
            }

            @Override
            public void onMenuCollapsed() {
                Log.d(TAG, "--collapsed");
            }
        });
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "--camera");
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePicture.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        fabList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "--list");
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Log.d(TAG, "--activity result OK");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            FirebaseVisionImage fvi = FirebaseVisionImage.fromBitmap(imageBitmap);

            Log.d(TAG, "--calling OCR");
            FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
            textRecognizer.processImage(fvi).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    StringBuilder sb = new StringBuilder();
                    for (FirebaseVisionText.TextBlock textBlock: firebaseVisionText.getTextBlocks()){
                        Log.d(TAG, "--block" + textBlock.getText());
                        for (FirebaseVisionText.Line line : textBlock.getLines()){
                            Log.d(TAG, "--line" + line.getText());
                            for (FirebaseVisionText.Element element : line.getElements()){
                                Log.d(TAG, "--element " + element.getText());
                                sb.append(element.getText()).append("\n");
                            }
                        }
                    }
                    String result = sb.toString();
                    Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
                    Log.d(TAG, result);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "--failure");
                    Log.e(TAG, "--failure");
                    Log.e(TAG, e.getLocalizedMessage());
                }
            });
        }
    }
}
