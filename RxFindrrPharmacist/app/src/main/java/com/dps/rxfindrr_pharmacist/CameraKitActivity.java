package com.dps.rxfindrr_pharmacist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dps.rxfindrr_pharmacist.controllers.VersionController;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;


public class CameraKitActivity extends AppCompatActivity {

    private static final String TAG = "CAMERAKIT";

    private CameraView cameraView;
    private Button btnPicture;

    private VersionController visionController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_kit);

        cameraView = findViewById(R.id.id_ck_camera);
        btnPicture = findViewById(R.id.picture);

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {
                Log.d(TAG, "--onEvent " + cameraKitEvent);
            }

            @Override
            public void onError(CameraKitError cameraKitError) {
                Log.e(TAG, "--onError " + cameraKitError.getMessage(), cameraKitError.getException());
            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                Log.d(TAG, "--onImage " + cameraKitImage);
                FirebaseVisionImage fvi = FirebaseVisionImage.fromBitmap(cameraKitImage.getBitmap());
                FirebaseVisionBarcodeDetectorOptions options = new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_ALL_FORMATS).build();

                visionController = new VersionController(CameraKitActivity.this, fvi);
                visionController.decodeQR(options);
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {
                Log.d(TAG, "--onVideo " + cameraKitVideo);
            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.captureImage();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    public void passOCRData(final String qrValue){
        Log.d(TAG, "--passOCRData " + qrValue);
        Intent orderDetailsIntent = new Intent (this, OrderDetailsActivity.class);
        orderDetailsIntent.putExtra("qrvalues", qrValue);
        this.startActivity(orderDetailsIntent);
    }
}
