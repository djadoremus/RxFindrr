package com.dps.rxfindrr_user;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dps.rxfindrr_user.controllers.VisionController;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.wonderkiln.camerakit.CameraKit;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.List;

public class CameraKitActivity extends AppCompatActivity {

    private static final String TAG = "CAMERAKIT";

    private CameraView cameraView;
    private Button btnPicture;

    private VisionController visionController;

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
                visionController = new VisionController(CameraKitActivity.this, fvi);
                visionController.textRecognition();
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

    public void passOCRData(final String[] lines){
        Log.d(TAG, "--passOCRData " + lines.length);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Data");
        builder.setItems(lines, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "--selected item : "  + lines[i]);
                Toast.makeText(CameraKitActivity.this, lines[i], Toast.LENGTH_LONG).show();
                Intent data = new Intent();
                data.putExtra("selectedLine", lines[i]);
                setResult(RESULT_OK, data);
                CameraKitActivity.this.finish();
            }
        });
        builder.show();
    }
}
