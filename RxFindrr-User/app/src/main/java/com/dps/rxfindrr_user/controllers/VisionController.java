package com.dps.rxfindrr_user.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dps.rxfindrr_user.Camera2ActivityFragment;
import com.dps.rxfindrr_user.CameraKitActivity;
import com.dps.rxfindrr_user.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.ArrayList;
import java.util.List;

public class VisionController {

    private static final String TAG = "VISIONCONTROLLER";
    private Context context;
    private FirebaseVisionImage image;

    public VisionController(Context context, FirebaseVisionImage image){
        this.context = context;
        this.image = image;
    }

    public void decodeQR(FirebaseVisionBarcodeDetectorOptions options){
        Log.d(TAG, "--decodeQR");
        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options);
        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
            .addOnCompleteListener(new OnCompleteListener<List<FirebaseVisionBarcode>>() {
                @Override
                public void onComplete(@NonNull Task<List<FirebaseVisionBarcode>> task) {
                    Log.d(TAG, task.isCanceled() + " | " + task.isComplete() + " | " + task.isSuccessful());
                    if (task.isSuccessful()){
                        Log.d(TAG, "size " + task.getResult().size());
                        StringBuilder sb = new StringBuilder();
                        for (FirebaseVisionBarcode barcode : task.getResult()){
                            sb.append(barcode.getRawValue());
                        }
                        String result = sb.toString();
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "result = " + result);
                    }
            }
//                        .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
//                @Override
//                public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
//                    StringBuilder sb = new StringBuilder();
//                    for (FirebaseVisionBarcode barcode : barcodes){
//                        sb.append(barcode.getRawValue());
//                    }
//                    String result = sb.toString();
//                    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
//                    Log.d(TAG, result);
//                }
//            })
//            .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.e(TAG, e.getLocalizedMessage());
//                }
            });
    }

    public void textRecognition(){
        Log.d(TAG, "--textRecognition");
        FirebaseVisionTextRecognizer textRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        textRecognizer.processImage(image)
            .addOnCompleteListener(new OnCompleteListener<FirebaseVisionText>() {
                @Override
                public void onComplete(@NonNull Task<FirebaseVisionText> task) {
                    Log.d(TAG, task.isCanceled() + " | " + task.isComplete() + " | " + task.isSuccessful());
                    List<String> lines = new ArrayList<>();
                    if (task.isSuccessful()){
                        StringBuilder sb = new StringBuilder();
                        for (FirebaseVisionText.TextBlock block : task.getResult().getTextBlocks()){
                            sb.append(block.getText());
                            for (FirebaseVisionText.Line line : block.getLines()){
                                lines.add(line.getText());
                            }
                        }
                        String result = sb.toString();
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "result = " + result);


                        String[] saLines = new String[lines.size()];
                        for (int i=0; i<lines.size(); i++){
                            saLines[i] = lines.get(i);
                        }

                        if (context instanceof CameraKitActivity){
                            Log.d(TAG, "--got instance of context");
                            ((CameraKitActivity)context).passOCRData(saLines);
                        }

                    }
                }
            });
    }

}
