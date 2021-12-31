package com.zahwaoctavioliena.ppb.uas.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zahwaoctavioliena.ppb.uas.Adapter.RecRiwayatAdapter;
import com.zahwaoctavioliena.ppb.uas.Database.DbHelper;
import com.zahwaoctavioliena.ppb.uas.Model.Produk;
import com.zahwaoctavioliena.ppb.uas.Model.ProdukRiwayat;
import com.zahwaoctavioliena.ppb.uas.R;

import java.util.List;

public class RiwayatFragment extends Fragment {

    //Inisialisasi objek
    DbHelper dbHelper;
    RecRiwayatAdapter adapter;

    //Inisialisasi tampilan view
    View rootView;
    RecyclerView recRiwayat;
    ImageView ivRiwayatKosong;
    TextView tvRiwayatKosongTitle, tvRiwayatKosongDesc;

    //Inisialisasi variabel
    List<ProdukRiwayat> produkList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_riwayat, container, false);

        initView();

        return rootView;
    }

    private void initView() {
        dbHelper = new DbHelper(getContext());

        recRiwayat = rootView.findViewById(R.id.rec_riwayat);
        ivRiwayatKosong = rootView.findViewById(R.id.iv_riwayat_kosong);
        tvRiwayatKosongTitle = rootView.findViewById(R.id.tv_riwayat_kosong_title);
        tvRiwayatKosongDesc = rootView.findViewById(R.id.tv_riwayat_kosong_description);

        //Menyembunyikan informasi produk kosong
        ivRiwayatKosong.setVisibility(View.GONE);
        tvRiwayatKosongTitle.setVisibility(View.GONE);
        tvRiwayatKosongDesc.setVisibility(View.GONE);

        initRecData();
    }

    private void initRecData() {
        produkList = dbHelper.getAllHistory("");

        if (produkList.size() == 0) {
            ivRiwayatKosong.setVisibility(View.VISIBLE);
            tvRiwayatKosongTitle.setVisibility(View.VISIBLE);
            tvRiwayatKosongDesc.setVisibility(View.VISIBLE);
        }
        adapter = new RecRiwayatAdapter(getContext(), produkList);

        recRiwayat.setLayoutManager(new LinearLayoutManager(getContext()));
        recRiwayat.setAdapter(adapter);
    }
}
