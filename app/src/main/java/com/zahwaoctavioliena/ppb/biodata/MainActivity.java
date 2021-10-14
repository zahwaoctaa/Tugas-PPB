package com.zahwaoctavioliena.ppb.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAlamat = (Button)findViewById(R.id.btnAlamat);
        Button btnTelpon = (Button)findViewById(R.id.btnTelpon);
        Button btnEmail = (Button)findViewById(R.id.btnEmail);
        btnAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uri = Uri.parse("geo:-7.582122, 110.072828?z=30");
                getData(uri);
            }
        });
        btnTelpon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uri = Uri.parse("tel:082264044684");
                getData(uri);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uri = Uri.parse("mailto:zahwa.octaa@gmail.com");
                getData(uri);
            }
        });
    }

    public void getData(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}