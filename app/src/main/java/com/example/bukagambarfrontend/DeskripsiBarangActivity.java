package com.example.bukagambarfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.graphics.Typeface;
import android.widget.TextView;

public class DeskripsiBarangActivity extends AppCompatActivity {

    ImageButton buttonCloseDeskrispiBarang;
    Button buttonLanjutkanDeskripsiBarang;
    EditText buttondeskripsiedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_barang);

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



        buttondeskripsiedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditTextDeskripsiActivity.class);
                startActivity(intent);
            }
        });



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
