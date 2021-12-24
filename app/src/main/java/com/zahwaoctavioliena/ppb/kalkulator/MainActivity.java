package com.zahwaoctavioliena.ppb.kalkulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KalkulatorAdapter adapter;
    private ArrayList<Kalkulator> kalkulatorArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rec_kalkulator);
        adapter = new KalkulatorAdapter(kalkulatorArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        Button btnHitung = (Button) findViewById(R.id.btnHitung);
        EditText Angka1 = (EditText) findViewById(R.id.Angka1);
        EditText Angka2 = (EditText) findViewById(R.id.Angka2);
        RadioButton btnTambah = (RadioButton) findViewById(R.id.btnTambah);
        RadioButton btnKurang = (RadioButton) findViewById(R.id.btnKurang);
        RadioButton btnKali = (RadioButton) findViewById(R.id.btnKali);
        RadioButton btnBagi = (RadioButton) findViewById(R.id.btnBagi);
        TextView txtHasil = (TextView) findViewById(R.id.txtHasil);

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(btnTambah.isChecked()){
                        Double hasil = Double.parseDouble(Angka1.getText().toString()) + Double.parseDouble(Angka2.getText().toString());
                        txtHasil.setText(hasil.toString());
                        addData(Angka1.getText().toString(), "+", Angka2.getText().toString(), hasil.toString());
                    } else if(btnKurang.isChecked()){
                        Double hasil = Double.parseDouble(Angka1.getText().toString()) - Double.parseDouble(Angka2.getText().toString());
                        txtHasil.setText(hasil.toString());
                        addData(Angka1.getText().toString(), "-", Angka2.getText().toString(), hasil.toString());
                    } else if(btnKali.isChecked()){
                        Double hasil = Double.parseDouble(Angka1.getText().toString()) * Double.parseDouble(Angka2.getText().toString());
                        txtHasil.setText(hasil.toString());
                        addData(Angka1.getText().toString(), "*", Angka2.getText().toString(), hasil.toString());
                    } else if(btnBagi.isChecked()){
                        Double hasil = Double.parseDouble(Angka1.getText().toString()) / Double.parseDouble(Angka2.getText().toString());
                        txtHasil.setText(hasil.toString());
                        addData(Angka1.getText().toString(), "/", Angka2.getText().toString(), hasil.toString());
                    }
                } catch(Exception e) {
                    builder.setMessage("Masih ada data yang kosong!");
                    builder.setTitle("Peringatan !");
                    builder.setCancelable(false);
                    builder
                            .setNegativeButton(
                                    "Ok",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which)
                                        {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    void addData(String angka1, String operator, String angka2, String hasil){
        kalkulatorArrayList = new ArrayList<>();
        kalkulatorArrayList.add(new Kalkulator(angka1, operator, angka2, hasil));

        adapter = new KalkulatorAdapter(kalkulatorArrayList);
        recyclerView.setAdapter(adapter);
    }
}