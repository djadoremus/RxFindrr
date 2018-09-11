package com.dps.rxfindrr_user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class BatchResultAdapter extends ArrayAdapter<BatchResult> {

    private static final String TAG = "BatchResultAdapter";

    private Context mContext;
    int mResource;

    public BatchResultAdapter (Context context, int resource, ArrayList<BatchResult> objects){
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // gets Batch results information
        String prescription = getItem(position).getStrPrescription();
        String brandName = getItem(position).getStrBrName();
        String quantity = getItem(position).getStrQuantity();

        // Create the Batch Result object with information
        BatchResult batchresult = new BatchResult(prescription, brandName, quantity);

        LayoutInflater inflater =  LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        EditText edPrescription = (EditText) convertView.findViewById(R.id.tvSearchPrescription);
        EditText edBrandName = (EditText) convertView.findViewById(R.id.tvSpecificBrandName);
        EditText edQuantity = (EditText) convertView.findViewById(R.id.Quantity);

        edPrescription.setText(prescription);
        edBrandName.setText(brandName);
        edQuantity.setText(quantity);

        return convertView;
    }

}
