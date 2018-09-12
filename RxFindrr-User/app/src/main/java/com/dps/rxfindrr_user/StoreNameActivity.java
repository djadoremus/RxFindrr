package com.dps.rxfindrr_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dps.rxfindrr_user.Adapter.StoreNameAdapter;
import com.dps.rxfindrr_user.Model.StoreModel;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

public class StoreNameActivity extends AppCompatActivity implements View.OnClickListener {

    private FButton btnPay;
    private FButton btnReserve;
    private FButton btnContinueShopping;

    ArrayList<StoreModel> storeList;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_name);

        recyclerView = findViewById(R.id.rv);

        storeList = new ArrayList<>();
        storeList.add(new StoreModel(R.drawable.rx, R.drawable.rx, R.drawable.rx, "storename1", "brand1", "quantity1"));
        storeList.add(new StoreModel(R.drawable.rx, R.drawable.rx, R.drawable.rx, "storename2", "brand2", "quantity2"));
        storeList.add(new StoreModel(R.drawable.rx, R.drawable.rx, R.drawable.rx, "storename3", "brand3", "quantity3"));
        storeList.add(new StoreModel(R.drawable.rx, R.drawable.rx, R.drawable.rx, "storename4", "brand4", "quantity4"));
        storeList.add(new StoreModel(R.drawable.rx, R.drawable.rx, R.drawable.rx, "storename5", "brand5", "quantity5"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLManager = layoutManager;
        recyclerView.setLayoutManager(rvLManager);

        StoreNameAdapter adapter = new StoreNameAdapter (this,storeList);
        recyclerView.setAdapter(adapter);

        btnContinueShopping = findViewById(R.id.id_fbtn_continue);
        btnPay = findViewById(R.id.id_fbtn_pay);
        btnReserve = findViewById(R.id.id_fbtn_reserve);

        btnContinueShopping.setOnClickListener(this);
        btnPay.setOnClickListener(this);
        btnReserve.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent orderCompleteActivity = new Intent(this, OrderCompleteActivity.class);
        switch (v.getId()){
            case R.id.id_fbtn_continue:
                finish();
                break;
            case R.id.id_fbtn_pay:
                startActivity(orderCompleteActivity);
                break;
            case R.id.id_fbtn_reserve:
                startActivity(orderCompleteActivity);
                break;
        }
    }
}
