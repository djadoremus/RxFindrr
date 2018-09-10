package com.dps.rxfindrr_user.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dps.rxfindrr_user.OrderDirectionsActivity;
import com.dps.rxfindrr_user.R;
import com.dps.rxfindrr_user.models.Orders;
import com.dps.rxfindrr_user.models.PrescribedMedicine;
import com.dps.rxfindrr_user.models.PrescriptionMedicine;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderDetailsAdapter extends BaseAdapter {

    private String TAG = "ORDERDETAILS";

    private Activity a;
    private ArrayList<Orders> orders;

    private LayoutInflater inflater;

    public OrderDetailsAdapter(Activity a, ArrayList<Orders> orders){
        Log.d(TAG, "--constructor | " + orders.size());
        this.a = a;
        this.orders = orders;

        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "--getView");
        View vi = view;
        if (view == null){
            vi = inflater.inflate(R.layout.list_orderdetails, null);
        }

        ImageView qrCode = (ImageView)vi.findViewById(R.id.l_img_id_qrcode);
        ListView llPrescriptions = (ListView)vi.findViewById(R.id.l_lv_id_prescriptionlist);
        TextView tvDrugstore = (TextView)vi.findViewById(R.id.l_tv_id_drugstore);
        TextView tvAddress = (TextView)vi.findViewById(R.id.l_tv_id_address);
        Button btnGoToStore = (Button)vi.findViewById(R.id.l_btn_id_directions);

        final Orders order = orders.get(i);
        Log.d(TAG, "prescribedMedicines " + order.getlPrescribedMedicines().size());
        ArrayList<String> prescribedList = new ArrayList<>();
        for (int j=0; j<order.getlPrescribedMedicines().size(); j++){
            PrescribedMedicine prescribedMedicine = order.getlPrescribedMedicines().get(j);
            PrescriptionMedicine prescriptionMedicine = prescribedMedicine.getPrescriptionMedicine();
            prescribedList.add(prescriptionMedicine.getBrandName() + " | " +
                    prescriptionMedicine.getDosage() + " | " +
                    prescribedMedicine.getQuantity());
        }
        tvDrugstore.setText(order.getDrugstore());
        llPrescriptions.setAdapter(new ArrayAdapter<>(a, android.R.layout.simple_list_item_1, android.R.id.text1, prescribedList.toArray()));

        btnGoToStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDirections = new Intent(a, OrderDirectionsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("orderid", order.getUid());
                a.startActivity(intentDirections, bundle);
            }
        });

        return vi;
    }
}
