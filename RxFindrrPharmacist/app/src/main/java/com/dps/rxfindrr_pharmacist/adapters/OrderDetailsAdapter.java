package com.dps.rxfindrr_pharmacist.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dps.rxfindrr_pharmacist.R;
import com.dps.rxfindrr_pharmacist.models.Orders;
import com.dps.rxfindrr_pharmacist.models.PrescribedMedicine;
import com.dps.rxfindrr_pharmacist.models.PrescriptionMedicine;
import com.google.zxing.*;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;

import static com.google.zxing.BarcodeFormat.QR_CODE;

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
        TextView tvStatus = (TextView)vi.findViewById(R.id.l_tv_id_status);

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
        tvStatus.setText(order.getPaid() ? "Paid!" : "Reserved");
        llPrescriptions.setAdapter(new ArrayAdapter<>(a, android.R.layout.simple_list_item_1, android.R.id.text1, prescribedList.toArray()));

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            Integer ivPx = convertDpToPx(150);
            BitMatrix qrMatrix = qrCodeWriter.encode(order.getUid(), QR_CODE, ivPx, ivPx);
            int[] pixels = new int[ivPx* ivPx];
            for (int y = 0; y < ivPx; y++) {
                for (int x = 0; x < ivPx; x++) {
                    if (qrMatrix.get(x, y)) {
                        pixels[y * ivPx + x] = Color.BLACK;
                    } else {
                        pixels[y * ivPx + x] = Color.WHITE;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(ivPx, ivPx, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, ivPx, 0, 0, ivPx, ivPx);
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }


        return vi;
    }

    private Integer convertDpToPx(Integer dp){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Integer px = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_PX, dp, displaymetrics );

        return px;
    }
}
