package com.dps.rxfindrr_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchBrandActivity extends AppCompatActivity {

    private Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_brand);

        btnCart = findViewById(R.id.id_fbtn_cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent storeNameActivity = new Intent(SearchBrandActivity.this, StoreNameActivity.class);
                startActivity(storeNameActivity);
            }
        });
    }
}
