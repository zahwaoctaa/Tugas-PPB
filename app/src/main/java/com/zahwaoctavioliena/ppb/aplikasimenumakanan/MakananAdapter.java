package com.zahwaoctavioliena.ppb.aplikasimenumakanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {

    public MakananAdapter(ArrayList<Makanan> listMakanan) {
        this.listMakanan = listMakanan;
    }

    private ArrayList<Makanan> listMakanan;
    private OnMakananListener onMakananListener;

    @NonNull
    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_makanan, parent, false));

        try {
            this.onMakananListener = ((OnMakananListener)context);
        } catch (ClassCastException e){
            throw new ClassCastException(e.getMessage());
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.ViewHolder holder, int position) {
        Makanan makanan = listMakanan.get(position);
        holder.txtNama.setText(makanan.getNama());
        holder.txtHarga.setText(Integer.toString(makanan.getHarga()));
        holder.imgMakanan.setImageResource(makanan.getId_gambar());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra("makanan", makanan.getNama());
                intent.putExtra("harga", makanan.getHarga());
                intent.putExtra("deskripsi", makanan.getDeskripsi());
                intent.putExtra("idGambar", makanan.getId_gambar());

                onMakananListener.onMakananListener(intent);
//                Toast.makeText(holder.itemView.getContext(), makanan.getDeskripsi(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNama, txtHarga, txtDeskripsi;
        public ImageView imgMakanan;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = (TextView) itemView.findViewById(R.id.txtNama);
            txtHarga = (TextView) itemView.findViewById(R.id.txtHarga);
            imgMakanan = (ImageView) itemView.findViewById(R.id.imgMakanan);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.mainLayout);
        }
    }

    public interface OnMakananListener{
        public void onMakananListener(Intent intent);
    }
}
