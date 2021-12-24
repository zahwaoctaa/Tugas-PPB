package com.zahwaoctavioliena.ppb.kalkulator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KalkulatorAdapter extends RecyclerView.Adapter<KalkulatorAdapter.KalkulatorViewHolder> {
    private ArrayList<Kalkulator> dataList;

    public KalkulatorAdapter(ArrayList<Kalkulator> dataList) {
        this.dataList = dataList;
    }

    @Override
    public KalkulatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_kalkulator, parent, false);
        return new KalkulatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KalkulatorViewHolder holder, int position) {
        holder.txtAngka1.setText(dataList.get(position).getAngka1());
        holder.txtOperator.setText(dataList.get(position).getOperator());
        holder.txtAngka2.setText(dataList.get(position).getAngka2());
        holder.txtHasil1.setText(dataList.get(position).getHasil());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class KalkulatorViewHolder extends RecyclerView.ViewHolder{
        private TextView txtAngka1, txtAngka2, txtOperator, txtHasil1;

        public KalkulatorViewHolder(View itemView) {
            super(itemView);
            txtAngka1 = (TextView) itemView.findViewById(R.id.txtAngka1);
            txtOperator = (TextView) itemView.findViewById(R.id.txtOperator);
            txtAngka2 = (TextView) itemView.findViewById(R.id.txtAngka2);
            txtHasil1 = (TextView) itemView.findViewById(R.id.txtHasil1);
        }
    }
}
