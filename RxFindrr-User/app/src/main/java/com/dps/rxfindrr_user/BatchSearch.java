package com.dps.rxfindrr_user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.ArrayList;


public class BatchSearch extends AppCompatActivity {

    private static final String TAG = "Batch Search";

    String genName, brName, quan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batch_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Start FDL call
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "getDynamicLink:onFailure", e);
                    }
                });

        // END FDL call

        final ListView mListView = (ListView) findViewById(R.id.lsbatchList);

        //Get items from List
        final BatchResult item1 = new BatchResult(null, null, null);

        final ArrayList<BatchResult> resultsList = new ArrayList<>();
        resultsList.add(item1);

        final BatchResultAdapter adapter = new BatchResultAdapter(this, R.layout.adapter_batch_search, resultsList);
        mListView.setAdapter(adapter);

        //Method to add another set of Batch Search
        Button btnID = (Button) findViewById(R.id.btnAdd);
        btnID.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultsList.add(item1);
                        adapter.notifyDataSetChanged();
                    }
                });

        Button btnSearch = (Button) findViewById(R.id.btnBatchSearch);


        btnSearch.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int count = mListView.getChildCount();
                        for (int i = 0; i < count; i++) {

                            View qwe = mListView.getChildAt(i);
                            genName = ((EditText) qwe.findViewById(R.id.tvSearchPrescription)).getText().toString();
                            brName = ((EditText) qwe.findViewById(R.id.tvSpecificBrandName)).getText().toString();
                            quan = ((EditText) qwe.findViewById(R.id.Quantity)).getText().toString();

                            Log.d(TAG, "Generic name: " + genName);
                            Log.d(TAG, "Brand name: " + brName);
                            Log.d(TAG, "Quantiy: " + quan);

                        }

                        Intent myIntent = new Intent(BatchSearch.this, OrderCompleteActivity.class);
                        BatchSearch.this.startActivity(myIntent);
                        myIntent.putExtra("brandname", genName);
                        myIntent.putExtra("genericname", brName);
                        myIntent.putExtra("quantity", quan);
                        startActivity(myIntent);
                        
                    }
                }
        );
    }
}
