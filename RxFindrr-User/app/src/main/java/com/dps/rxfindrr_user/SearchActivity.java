package com.dps.rxfindrr_user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dps.rxfindrr_user.Model.SampleStoreModel;
import com.dps.rxfindrr_user.Model.StoreModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {


    FirebaseFirestore db;
    EditText etText;
    Button button;
    Context mContext;
    TextView tvser;
    DocumentReference documentReference;
    public final String BRAND_KEY = "BrandName";
    public final String MANUFACTURER_KEY = "Manufacturer";

    StoreModel sampleStoreModels;

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mContext = this;
        db = FirebaseFirestore.getInstance();
//        firebaseFirestorel = FirebaseFirestore.getInstance();
//        CollectionReference brandName = firebaseFirestorel.collection("products");


        tvser = (TextView) findViewById(R.id.tvser);
        etText = (EditText) findViewById(R.id.etUser);
        button = (Button) findViewById(R.id.btnClick);

    }

    // FireStore
    private void addDetails() {

        String uname = etText.getText().toString();

        Log.e(TAG, "addDetails: " + etText.getText().toString());
//        Map<String, Object> newUser = new HashMap<>();

        SampleStoreModel sampleStoreModel = new SampleStoreModel(uname);


//        newUser.put(BRAND_KEY, uname);
//        newUser.put(MANUFACTURER_KEY, "Manufacturer");

        db.collection("brand").add(sampleStoreModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.e(TAG, "onSuccess: " + "success");
                Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String error = e.getMessage();
                Log.e(TAG, "onFailure: " + error);
                Toast.makeText(mContext, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Read FirebaseStore

    private void ReadUserDetails() {

        DocumentReference storename = db.collection("Brand").document("name");
        storename.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    fields.append("BrandName:").append(doc.get("BrandName"));
                    tvser.setText(fields.toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "Failed to get data !", Toast.LENGTH_SHORT).show();
            }
        });


    }


    //Update Users

    public void UpdateData() {


        DocumentReference userdata = db.collection("Brand").document("name");

        userdata.update(BRAND_KEY, 7654321).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(mContext, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext, "Update Failed !", Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "button", Toast.LENGTH_SHORT).show();
                addDetails();
            }
        });

    }
}