package com.dps.rxfindrr_pharmacist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dps.rxfindrr_pharmacist.adapters.OrderDetailsAdapter;
import com.dps.rxfindrr_pharmacist.models.Orders;
import com.dps.rxfindrr_pharmacist.models.PrescribedMedicine;
import com.dps.rxfindrr_pharmacist.models.PrescriptionMedicine;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    private Orders stubOrder;

    private TextView tvOrderId;
    private ListView lvPrescribedMedicine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        mock order
         */
        stubOrder = new Orders();
        stubOrder.setUid("foobar");
        stubOrder.setDrugstore("Lorem Ipsum");
        stubOrder.setQrCodeUrl("http://adipiscing.elit/img.png");
        stubOrder.setPaid(Boolean.FALSE);

        List<PrescribedMedicine> prescribedMedicine = new ArrayList<PrescribedMedicine>();
        for (int i=0; i<3; i++){
            PrescribedMedicine pm = new PrescribedMedicine();

            PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
            prescriptionMedicine.setUid("prescriptionMedicine " + i);
            prescriptionMedicine.setBrandName("Dolor Sit");
            prescriptionMedicine.setGenericName("Consectetur Adipiscing");
            prescriptionMedicine.setImgUrl("http://amet.com/img.png");
            prescriptionMedicine.setDosage("100mg");

            pm.setUid("pm00000" + i);
            pm.setQuantity(3);
            pm.setPrescriptionMedicine(prescriptionMedicine);

            prescribedMedicine.add(pm);
        }
        stubOrder.setlPrescribedMedicines(prescribedMedicine);

        /*******************/
        tvOrderId = findViewById(R.id.id_tv_order_val);
        lvPrescribedMedicine = findViewById(R.id.id_lv_prescribedlist);

        ArrayList<Orders> orders = new ArrayList<>();
        orders.add(stubOrder);

        lvPrescribedMedicine.setAdapter(new OrderDetailsAdapter(this, orders));
        tvOrderId.setText(stubOrder.getUid());
    }

}
