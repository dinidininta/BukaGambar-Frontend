package com.example.bukagambarfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class NamaBarangActivity extends AppCompatActivity {

    ImageButton buttonCloseNamaBarang;
    Button buttonLanjutkanNamaBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_barang);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_namabarangActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        //button close
        buttonCloseNamaBarang = (ImageButton) findViewById(R.id.close_namabarang_button);

        buttonCloseNamaBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        //button lanjutkan
        buttonLanjutkanNamaBarang = (Button) findViewById(R.id.lanjutkan_namabarang_button);

        buttonLanjutkanNamaBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KategoriBarangActivity.class);
                startActivity(intent);
            }
        });
    }
}
