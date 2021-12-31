package com.zahwaoctavioliena.ppb.uas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.zahwaoctavioliena.ppb.uas.Activity.BantuanActivity;
import com.zahwaoctavioliena.ppb.uas.Activity.IntroActivity;
import com.zahwaoctavioliena.ppb.uas.Activity.TentangActivity;
import com.zahwaoctavioliena.ppb.uas.Fragment.KeranjangFragment;
import com.zahwaoctavioliena.ppb.uas.Fragment.ProdukFragment;
import com.zahwaoctavioliena.ppb.uas.Fragment.RiwayatFragment;
import com.zahwaoctavioliena.ppb.uas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //Inisialisasi objek;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPref;
    static String KEY_USER = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString(KEY_USER, null);


        //Inisialisasi Koneksi View
        bottomNavigationView = findViewById(R.id.bottom_nav_main);

        //Menjalankan fragment produk sebagai yang pertama
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new ProdukFragment()).commit();
        getSupportActionBar().setTitle("Produk");


        //Fungsi untuk menampilkan fragment berdasarkan pilihan menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_produk:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new ProdukFragment()).commit();
                        getSupportActionBar().setTitle("Produk");
                        break;
                    case R.id.menu_keranjang:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new KeranjangFragment()).commit();
                        getSupportActionBar().setTitle("Keranjang");
                        break;
                    case R.id.menu_riwayat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new RiwayatFragment()).commit();
                        getSupportActionBar().setTitle("Riwayat");
                        break;
                }
                return true;
            }
        });

    }

    //Method untuk mengisi menu Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    //Method untuk menu Toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_menu_bantuan :
                Intent intentMenuBantuan = new Intent(getApplicationContext(), BantuanActivity.class);
                startActivity(intentMenuBantuan);
                break;
            case R.id.toolbar_menu_tentang :
                Intent intentMenuTentang = new Intent(getApplicationContext(), TentangActivity.class);
                startActivity(intentMenuTentang);
                break;
            case R.id.toolbar_menu_logout :
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(KEY_USER);
                editor.apply();
                Intent intentMenuLogout = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentMenuLogout);
                break;
        }
        return false;
    }
}