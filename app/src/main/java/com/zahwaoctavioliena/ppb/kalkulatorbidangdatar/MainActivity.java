package com.zahwaoctavioliena.ppb.kalkulatorbidangdatar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent(){
        Button btnPsg = (Button)findViewById(R.id.btnPsg);
        Button btnSgt = (Button)findViewById(R.id.btnSgt);
        Button btnLkr = (Button)findViewById(R.id.btnLkr);

        EditText editPAD = (EditText)findViewById(R.id.editPAD);
        EditText editLT = (EditText)findViewById(R.id.editLT);

        TextView txtKeliling = (TextView)findViewById(R.id.txtKeliling);
        TextView txtLuas = (TextView)findViewById(R.id.txtLuas);

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        btnPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Persegi psg = new Persegi();
                    Double keliling = psg.keliling(Double.parseDouble(editPAD.getText().toString()));
                    Double luas = psg.luas(Double.parseDouble(editPAD.getText().toString()), Double.parseDouble(editLT.getText().toString()));

                    txtLuas.setText(luas.toString());
                    txtKeliling.setText(keliling.toString());
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

                    txtLuas.setText("");
                    txtKeliling.setText("");
                }
            }
        });

        btnSgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Segitiga sgt = new Segitiga();
                    Double keliling = sgt.keliling(Double.parseDouble(editPAD.getText().toString()));
                    Double luas = sgt.luas(Double.parseDouble(editPAD.getText().toString()), Double.parseDouble(editLT.getText().toString()));

                    txtLuas.setText(luas.toString());
                    txtKeliling.setText(keliling.toString());
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

                    txtLuas.setText("");
                    txtKeliling.setText("");
                }
            }
        });

        btnLkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Lingkaran lkr = new Lingkaran();
                    Double keliling = lkr.keliling(Double.parseDouble(editPAD.getText().toString()));
                    Double luas = lkr.luas(Double.parseDouble(editPAD.getText().toString()));

                    txtLuas.setText(luas.toString());
                    txtKeliling.setText(keliling.toString());
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

                    txtLuas.setText("");
                    txtKeliling.setText("");
                }
            }
        });
    }
}