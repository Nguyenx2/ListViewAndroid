package com.example.listviewnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewnc.Models.SanPham;

import java.text.DecimalFormat;

public class ProductDetail extends AppCompatActivity {

    TextView tvTenSP, tvGiaSP, tvMoTaSP;
    ImageView imgAnhSP;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        tvTenSP = findViewById(R.id.tvTenSP);
        tvGiaSP = findViewById(R.id.tvGiaSP);
        tvMoTaSP = findViewById(R.id.tvMoTaSP);
        imgAnhSP = findViewById(R.id.imgAnhSP);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String tenSP = bundle.getString("tenSP");
        Double giaSP = bundle.getDouble("giaSP");
        String moTaSP = bundle.getString("moTaSP");
        int anhSP = bundle.getInt("anhSP");
        tvTenSP.setText(tenSP);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        tvGiaSP.setText("Giá: " + decimalFormat.format(giaSP) + " VNĐ");
        tvMoTaSP.setText(moTaSP);
        imgAnhSP.setImageResource(anhSP);


    }
}