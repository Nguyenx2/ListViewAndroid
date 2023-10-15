package com.example.listviewnc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.listviewnc.Adapter.SanPhamAdapter;
import com.example.listviewnc.Models.SanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    ArrayList<SanPham> listSanPham;
    SanPhamAdapter adapterSanPham;

    Button btnThem;

    int selectedIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanPham = findViewById(R.id.lvSanPham);
        listSanPham = new ArrayList<>();
        listSanPham.add(new SanPham("SP001", "Áo sơ mi nam dài tay", 795000, R.drawable.img1
        , "FORM DÁNG: Regular Fit\n" +
                "THIẾT KẾ:\n" +
                "- Áo Polo phom dáng Regular fit suông nhẹ nhưng vẫn vừa vặn, tôn dáng tối đa khi mặc\n" +
                "- Thiết kế basic với cổ đứng chỉn chu, sắc đen dệt Jacquard họa tiết, tạo nên dấu ấn thanh lịch, thời thượng cho quý ông.\n" +
                "\n" +
                "CHẤT LIỆU:\n" +
                "- 97% Cotton giúp áo mềm nhẹ, thấm hút tốt, thoáng khí dù ở mùa nào trong năm, đồng thời vẫn giữ được độ đứng dáng vừa đủ\n" +
                "- 3% Spandex tạo độ co giãn cho áo"));

        listSanPham.add(new SanPham("SP002", "Áo polo nam ngắn tay", 750000, R.drawable.img2
                , "FORM DÁNG: Regular Fit\n" +
                "THIẾT KẾ:\n" +
                "- Áo Polo phom dáng Regular fit suông nhẹ nhưng vẫn vừa vặn, tôn dáng tối đa khi mặc\n" +
                "- Thiết kế basic với cổ đứng chỉn chu, sắc đen dệt Jacquard họa tiết, tạo nên dấu ấn thanh lịch, thời thượng cho quý ông.\n" +
                "\n" +
                "CHẤT LIỆU:\n" +
                "- 97% Cotton giúp áo mềm nhẹ, thấm hút tốt, thoáng khí dù ở mùa nào trong năm, đồng thời vẫn giữ được độ đứng dáng vừa đủ\n" +
                "- 3% Spandex tạo độ co giãn cho áo"));

        listSanPham.add(new SanPham("SP003", "Áo blazer nam", 2800000, R.drawable.img3
                , "KIỂU DÁNG: SLIM FIT\n" +
                "\n" +
                "CHI TIẾT:\n" +
                "\n" +
                "- Áo Blazer nam phom Slim fit ôm vừa vặn, tôn dáng người mặc nhưng vẫn đảm bảo thoải mái tối đa.\n" +
                "\n" +
                "- Thiết kế lịch lãm với 1 khuy cài, 4 khuy tay áo, đường cắt may tỉ mỉ cùng hiệu ứng caro thời thượng trên nền xanh tím than, mix&match cùng áo sơ mi hay áo t-shirt cũng đều tôn lên vẻ lịch thiệp, sang trọng đầy nam tính của quý ông thành đạt.\n" +
                "\n" +
                "CHẤT LIỆU:\n" +
                "\n" +
                "- 40% Polyester giúp áo balzer bền màu, sắc nét, giữ phom tốt, hạn chế co nhăn.\n" +
                "\n" +
                "- 33% Polyamide tạo độ mịn mượt đồng thời tối ưu khả năng thoát khí, không gây bí bách khi mặc.\n" +
                "\n" +
                "- 21% Rayon giúp áo balzer có độ mềm mại và bay rũ tự nhiên.\n" +
                "\n" +
                "- 6% Spandex tạo độ co giãn, thoải mái khi cử động"));

        listSanPham.add(new SanPham("SP004", "Áo blazer nam", 1560000, R.drawable.img4
                , "KIỂU DÁNG: CASUAL FIT\n" +
                "\n" +
                "CHI TIẾT:\n" +
                "\n" +
                "- Áo Blazer nam phom Casual fit suông rộng thoải mái, phù hợp với mọi dáng người, đem lại vẻ ngoài trẻ trung và thời thượng khi mặc.\n" +
                "\n" +
                "- Áo thiết kế lịch lãm với 1 khuy cài, 4 khuy tay áo, 2 đường xẻ tà truyền thống nhưng vẫn trẻ trung. Gam màu trung tính, kết hợp họa tiết kẻ tạo điểm nhấn nổi bật và thời thượng.\n" +
                "\n" +
                "CHẤT LIỆU:\n" +
                "\n" +
                "- 80% TC giúp áo bền màu, sắc nét, mặt vải trơn trượt, mỏng nhẹ. Ngoài ra áo còn có khả năng chống bám bụi, chống nhăn, hạn chế thấm nước, độ bền cao.\n" +
                "\n" +
                "- 20% Rayon mềm mại và giúp bộ suit có độ mềm mại, thoáng khí, bay rũ tự nhiên."));


        adapterSanPham = new SanPhamAdapter(MainActivity.this, R.layout.lv_san_pham, listSanPham);
        lvSanPham.setAdapter(adapterSanPham);

        btnThem = findViewById(R.id.btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                View addView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_add_sanpham, null);
                builder.setView(addView);

                EditText edtMaSP = addView.findViewById(R.id.edtMaSP);
                EditText edtTenSP = addView.findViewById(R.id.edtTenSP);
                EditText edtGiaSP = addView.findViewById(R.id.edtGiaSP);
                EditText edtDesc = addView.findViewById(R.id.edtMoTa);


                builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newMaSP = edtMaSP.getText().toString().trim();
                        String newTenSP = edtTenSP.getText().toString().trim();
                        String newDescSP = edtDesc.getText().toString().trim();
                        double newGiaSP = Double.parseDouble(edtGiaSP.getText().toString());

                        SanPham newSanPham = new SanPham(newMaSP, newTenSP, newGiaSP, R.drawable.default_image, newDescSP);
                        listSanPham.add(newSanPham);
                        adapterSanPham.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "Sản phẩm đã được thêm mới !", Toast.LENGTH_SHORT).show();
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
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchBar = menu.findItem(R.id.searchBar);
        SearchView searchView = (SearchView) searchBar.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                adapterSanPham.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterSanPham.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}