package com.dps.rxfindrr_user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dps.rxfindrr_user.Model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    Context mContext;
    FirebaseDatabase firebaseDatabase;
    Button btnSignup;

    EditText edtUsername, edtPassword, edtFirstname, edtMiddlename, edtLastname, edtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mContext = this;
        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtFirstname = (EditText) findViewById(R.id.edtFirstname);
        edtMiddlename = (EditText) findViewById(R.id.edtMiddlename);
        edtLastname = (EditText) findViewById(R.id.edtLastname);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        btnSignup = (Button) findViewById(R.id.btnSignUp);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUpOnClicks();
            }
        });

    }


    public void signUpOnClicks() {


        final ProgressDialog mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Please waiting...");
        mDialog.show();


        //  For Realtime database

        final DatabaseReference table_user = firebaseDatabase.getReference("rxfindrr");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //check if user not exist in database
                if (dataSnapshot.child(edtUsername.getText().toString()).exists()) {
                    mDialog.dismiss();
                    Toast.makeText(mContext, "This username number is already registered !", Toast.LENGTH_SHORT).show();

                } else {
                    mDialog.dismiss();
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();
                    String firstname = edtFirstname.getText().toString();
                    String middlename = edtMiddlename.getText().toString();
                    String lastname = edtLastname.getText().toString();
                    String address = edtAddress.getText().toString();


                    UserModel userModel = new UserModel(address, firstname, lastname, middlename, password, username);

                    table_user.child(edtUsername.getText().toString()).setValue(userModel);
                    Toast.makeText(mContext, "Sign up successfully !", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                    RegistrationActivity.this.startActivity(myIntent);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Log.e("Registration", "onCancelled: " + "");
            }
        });


    }


}
