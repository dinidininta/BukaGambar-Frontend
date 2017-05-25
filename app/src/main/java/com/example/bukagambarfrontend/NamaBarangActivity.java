package com.example.bukagambarfrontend;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class NamaBarangActivity extends AppCompatActivity {

    ImageButton buttonCloseNamaBarang;
    Button buttonLanjutkanNamaBarang;
    EditText namabarangET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_barang);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_namabarangActivity);
        //button close
        buttonCloseNamaBarang = (ImageButton) findViewById(R.id.close_namabarang_button);
        //button Edit Text
        namabarangET = (EditText) findViewById(R.id.editText_namabarang);
        //button lanjutkan
        buttonLanjutkanNamaBarang = (Button) findViewById(R.id.lanjutkan_namabarang_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");


        buttonCloseNamaBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        buttonLanjutkanNamaBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KategoriBarangActivity.class);
                startActivity(intent);
            }
        });

        buttonLanjutkanNamaBarang.setEnabled(false);


        namabarangET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().equals("")){
                    buttonLanjutkanNamaBarang.setEnabled(false);
                }else {
                    buttonLanjutkanNamaBarang.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
