package com.example.bukagambar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class NamaBarang extends AppCompatActivity {

    ImageButton buttonCloseNamaBarang;
    Button buttonLanjutkanNamaBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_barang);

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
