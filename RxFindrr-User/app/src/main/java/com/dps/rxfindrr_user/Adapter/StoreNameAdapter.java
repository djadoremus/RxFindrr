package com.dps.rxfindrr_user.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dps.rxfindrr_user.Model.StoreModel;
import com.dps.rxfindrr_user.R;

import java.util.ArrayList;

public class StoreNameAdapter extends RecyclerView.Adapter<StoreNameAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<StoreModel> mlist;

    public StoreNameAdapter(Context context, ArrayList<StoreModel> list) {

        mContext = context;
        mlist = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_store_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        StoreModel storeModel = mlist.get(position);
        ImageView ivStore = holder.ivStoreImage;
        ImageView ivDelete = holder.ivDelete;
        ImageView ivEdit = holder.ivEdit;
        TextView tvBrand = holder.tvBrand;
        TextView tvQuantity = holder.tvQuantity;
        TextView tvStoreName = holder.tvStoreName;

        ivStore.setImageResource(storeModel.getImage());
        tvBrand.setText(storeModel.getBrand());
        tvQuantity.setText(storeModel.getQuantity());
        tvStoreName.setText(storeModel.getStoreName());


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView ivStoreImage, ivEdit, ivDelete;
        TextView tvBrand, tvQuantity, tvStoreName;

        public ViewHolder(View itemView) {
            super(itemView);

            ivStoreImage = itemView.findViewById(R.id.ivStoreImage);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            tvBrand = itemView.findViewById(R.id.tvBrandName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);


        }

    }
}
