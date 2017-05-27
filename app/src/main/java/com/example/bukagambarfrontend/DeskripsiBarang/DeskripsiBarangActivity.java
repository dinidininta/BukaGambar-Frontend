package com.example.bukagambarfrontend.DeskripsiBarang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukagambarfrontend.DetailBarang.DetailBarangActivity;
import com.example.bukagambarfrontend.KategoriBarang.KategoriBarangActivity;
import com.example.bukagambarfrontend.KategoriBarang.SubKategoriActivity;
import com.example.bukagambarfrontend.NamaBarangActivity;
import com.example.bukagambarfrontend.R;
import com.example.bukagambarfrontend.StepSatuActivity;

public class DeskripsiBarangActivity extends AppCompatActivity {

    ImageButton buttonCloseDeskrispiBarang;
    Button buttonLanjutkanDeskripsiBarang;
    EditText buttondeskripsiedit;
    TextView tv3, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_barang);

        String nb = NamaBarangActivity.nama_barang;
        String kb = KategoriBarangActivity.kategori_barang;
        String skb = SubKategoriActivity.sub_kat_barang;

        Toast.makeText(this, nb + " " + kb, Toast.LENGTH_LONG).show();

        //set font
        tv3=(TextView)findViewById(R.id.teks_3_deskripsibarang);
        tv5=(TextView)findViewById(R.id.teks_5_deskripsibarang);

        tv3.setText(nb);
        tv5.setText(kb + skb);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_deskripsiActivity);
        //EditText Deskripsi
        buttondeskripsiedit = (EditText) findViewById(R.id.editText_deskripsibarang);
        //button close
        buttonCloseDeskrispiBarang = (ImageButton) findViewById(R.id.close_deskripsibarang_button);
        //button lanjutkan
        buttonLanjutkanDeskripsiBarang = (Button) findViewById(R.id.lanjutkan_deskrispibarang_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");



//        try {
//            String db = EditTextDeskripsiActivity.desc_barang;
//            buttondeskripsiedit.setText(db);
//        }catch (NullPointerException e){
//
//        }

        buttondeskripsiedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditTextDeskripsiActivity.class);
                startActivity(intent);
            }
        });

        String db = EditTextDeskripsiActivity.desc_barang;
        if(!db.isEmpty()){
            buttondeskripsiedit.setText(db);
        }

        buttonCloseDeskrispiBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });


        buttonLanjutkanDeskripsiBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailBarangActivity.class);
                startActivity(intent);
            }
        });
    }
}
