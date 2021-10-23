package com.zahwaoctavioliena.ppb.aplikasimenumakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView nama, harga, deskripsi;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nama = findViewById(R.id.txtNama2);
        harga = findViewById(R.id.txtHarga2);
        deskripsi = findViewById(R.id.txtDeskripsi);
        gambar = findViewById(R.id.imgMakanan2);

        Bundle bundle = getIntent().getExtras();
        nama.setText(bundle.getString("makanan"));
        harga.setText(Integer.toString(bundle.getInt("harga")));
        deskripsi.setText(bundle.getString("deskripsi"));
        gambar.setImageResource(bundle.getInt("idGambar"));
    }
}