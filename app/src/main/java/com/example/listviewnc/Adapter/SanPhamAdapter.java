package com.example.listviewnc.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.listviewnc.MainActivity;
import com.example.listviewnc.Models.SanPham;
import com.example.listviewnc.ProductDetail;
import com.example.listviewnc.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter implements Filterable {
    Activity context;
    int resource;
    ArrayList<SanPham> listSanPham, listSanPhamBackup, listSanPhamFilter;


    public SanPhamAdapter(Activity context, int resource, ArrayList<SanPham> listSanPham) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.listSanPham = this.listSanPhamBackup = listSanPham;
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);

        ImageView imgLogo = customView.findViewById(R.id.imgLogo);
        TextView tvID = customView.findViewById(R.id.tvID);
        TextView tvName = customView.findViewById(R.id.tvName);
        TextView tvPrice = customView.findViewById(R.id.tvPrice);
        Button btnView = customView.findViewById(R.id.btnView);
        Button btnXoa = customView.findViewById(R.id.btnXoa);
        Button btnSua = customView.findViewById(R.id.btnSua);
        Button btnThem = customView.findViewById(R.id.btnThem);

        SanPham sp = this.listSanPham.get(position);

        imgLogo.setImageResource(sp.getLogoSP());
        tvID.setText(sp.getMaSP());
        tvName.setText(sp.getTenSP());

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        tvPrice.setText("Đơn giá: " + decimalFormat.format(sp.getGiaSP()) + " VND");

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("tenSP", sp.getTenSP());
                bundle.putDouble("giaSP", sp.getGiaSP());
                bundle.putString("moTaSP", sp.getDescription());
                bundle.putInt("anhSP", sp.getLogoSP());
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listSanPham.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sản phẩm đã được xóa !", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chỉnh sửa sản phẩm");

                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_sanpham, null);
                builder.setView(dialogView);

                TextView tvMaSP = dialogView.findViewById(R.id.tvMaSP);
                EditText edtTenSP = dialogView.findViewById(R.id.edtTenSP);
                EditText edtGiaSP = dialogView.findViewById(R.id.edtGiaSP);
                EditText edtMoTaSP = dialogView.findViewById(R.id.edtMoTaSP);

                SanPham sp = listSanPham.get(position);
                tvMaSP.setText("Mã sản phầm: " + sp.getMaSP());
                edtTenSP.setText(sp.getTenSP());
                edtGiaSP.setText(decimalFormat.format(sp.getGiaSP()));
                edtMoTaSP.setText(sp.getDescription());

                builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newTenSP = edtTenSP.getText().toString().trim();
                        double newGiaSP = Double.parseDouble(edtGiaSP.getText().toString());
                        String newMoTaSP = edtMoTaSP.getText().toString().trim();

                        sp.setTenSP(newTenSP);
                        sp.setGiaSP(newGiaSP);
                        sp.setDescription(newMoTaSP);

                        notifyDataSetChanged();

                        Toast.makeText(context, "Thông tin sản phẩm đã được thay đổi !", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return customView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase().trim();
                if (query.length() < 1) {
                    listSanPhamFilter = listSanPhamBackup;
                } else {
                    listSanPhamFilter = new ArrayList<>();
                    for (SanPham sp : listSanPham) {
                        if (sp.getMaSP().toLowerCase().contains(query) ||
                            sp.getTenSP().toLowerCase().contains(query)) {
                            listSanPhamFilter.add(sp);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listSanPhamFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listSanPham = (ArrayList<SanPham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
