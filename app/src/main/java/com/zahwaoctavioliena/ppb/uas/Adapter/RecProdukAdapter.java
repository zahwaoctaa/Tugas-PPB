package com.zahwaoctavioliena.ppb.uas.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zahwaoctavioliena.ppb.uas.Activity.MainActivity;
import com.zahwaoctavioliena.ppb.uas.Activity.TambahManualActivity;
import com.zahwaoctavioliena.ppb.uas.Database.DbHelper;
import com.zahwaoctavioliena.ppb.uas.Fragment.KeranjangFragment;
import com.zahwaoctavioliena.ppb.uas.Fragment.ProdukFragment;
import com.zahwaoctavioliena.ppb.uas.Model.Produk;
import com.zahwaoctavioliena.ppb.uas.R;

import java.util.List;

public class RecProdukAdapter extends RecyclerView.Adapter<RecProdukAdapter.ViewHolder> {

    //Inisialisasi objek
    Context context;
    List<Produk> listProduk;
    DbHelper dbHelper;

    //Konstruktor untuk meminta konteks
    public RecProdukAdapter(Context context, List<Produk> listProduk) {
        this.context = context;
        this.listProduk = listProduk;
        dbHelper = new DbHelper(context);
    }

    //Fungsi untuk tampilan recycleview
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.row_produk, parent, false);
        return new ViewHolder(rowView);
    }

    //Mengkoneksian adapter dengan tampilan (fungsi yang looping sebanyak data yang ada)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listProduk.get(position));
    }

    //Menampilkan jumlah susunan data berdasarkan pengulangan
    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    //Kelas utama ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Inisialisasi objek view
        TextView tvSKu, tvNama, tvHarga, tvStok;
        ImageView ivGambar;
        View rootView;

        //Inisialisasi variabel
        Uri uriGambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSKu = itemView.findViewById(R.id.tv_sku_produk);
            tvNama = itemView.findViewById(R.id.tv_nama_produk);
            tvHarga = itemView.findViewById(R.id.tv_harga_produk);
            tvStok = itemView.findViewById(R.id.tv_stok_produk);
            ivGambar = itemView.findViewById(R.id.iv_gambar);
            rootView = itemView.findViewById(R.id.root_row_produk);
        }

        //Fungsi untuk ngeset data di adapter (fungsi onBindViewHolder)
        void bindData(Produk produk) {
            tvSKu.setText(produk.getSku());
            tvNama.setText(produk.getNama());
            tvHarga.setText("Rp. " + produk.getHarga());
            tvStok.setText("Stok : " + produk.getStok());

            uriGambar = Uri.parse(produk.getGambar());
            ivGambar.setImageURI(uriGambar);

            //Fungsi saat item adapter diklik
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProdukDialog(produk);
                }
            });
        }

        //Fungsi untuk memunculkan dialog detail produk
        void showProdukDialog(Produk produk) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            View viewAlert = LayoutInflater.from(context).inflate(R.layout.view_produk_dialog, null, false);

            //Inisialisasi koneksi view
            ImageView ivDialogGambar= viewAlert.findViewById(R.id.iv_dialog_produk_gambar);
            TextView tvDialogSku = viewAlert.findViewById(R.id.tv_dialog_produk_sku);
            TextView tvDialogNama = viewAlert.findViewById(R.id.tv_dialog_produk_nama);
            TextView tvDialogHarga = viewAlert.findViewById(R.id.tv_dialog_produk_harga);
            TextView tvDialogStok = viewAlert.findViewById(R.id.tv_dialog_produk_stok);
            NumberPicker numberPicker = viewAlert.findViewById(R.id.number_picker_jumlah);

            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(Integer.parseInt(produk.getStok()));

            uriGambar = Uri.parse(produk.getGambar());
            ivDialogGambar.setImageURI(uriGambar);

            tvDialogSku.setText(produk.getSku());
            tvDialogNama.setText(produk.getNama());
            tvDialogHarga.setText("Rp. " + produk.getHarga());
            tvDialogStok.setText("Stok : " + produk.getStok());

            alert.setTitle("Detail Produk");
            alert.setView(viewAlert);

            alert.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (Integer.parseInt(produk.getStok()) > 0) {
                        int stok = Integer.parseInt(produk.getStok()) - numberPicker.getValue();

                        listProduk.set(getAdapterPosition(), new Produk(
                                produk.getId(),
                                produk.getSku(),
                                produk.getNama(),
                                produk.getHarga(),
                                String.valueOf(stok),
                                produk.getGambar()

                        ));
                        notifyDataSetChanged();

                        dbHelper.updateProduk(new Produk(
                                produk.getId(),
                                produk.getSku(),
                                produk.getNama(),
                                produk.getHarga(),
                                String.valueOf(stok),
                                produk.getGambar()
                        ));

                        dbHelper.addKeranjang(new Produk(
                                produk.getId(),
                                produk.getSku(),
                                produk.getNama(),
                                produk.getHarga(),
                                String.valueOf(numberPicker.getValue()),
                                produk.getGambar()
                        ));
                    } else {
                        Toast.makeText(context, "Stok Kosong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alert.setNegativeButton("Perbarui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, TambahManualActivity.class);
                    intent.putExtra("dataproduk", produk);
                    context.startActivity(intent);
                }
            });
            alert.setNeutralButton("Hapus", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    AlertDialog.Builder alertHapus = new AlertDialog.Builder(context);

                    alertHapus
                            .setTitle("Hapus Produk")
                            .setMessage("Yakin Hapus Produk ?")
                            .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listProduk.remove(produk);
                                    notifyDataSetChanged();
                                    dbHelper.deleteProduk(produk.getId());
                                    dbHelper.deleteKeranjang(produk.getId());
                                    ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new ProdukFragment()).commit();
                                }
                            })
                            .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog dialogHapus = alertHapus.create();
                    dialogHapus.getWindow().setBackgroundDrawableResource(R.drawable.rounded_bg);
                    dialogHapus.show();
                }
            });

            AlertDialog dialog = alert.create();

            //Fungsi untuk mematikan tombol tambah jika stok kosong
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    if (Integer.parseInt(produk.getStok()) > 0) {
                        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    } else {
                        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            });

            dialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_bg);
            dialog.show();
        }
    }
}
