package com.dps.rxfindrr_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dps.rxfindrr_user.Adapter.StoreNameAdapter;
import com.dps.rxfindrr_user.Model.StoreModel;

import java.util.ArrayList;

public class StoreNameActivity extends AppCompatActivity {
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


    }


}
