package com.example.bukagambarfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.graphics.Typeface;
import android.widget.TextView;

public class DeskripsiBarangActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5;
    ImageButton buttonCloseDeskrispiBarang;
    Button buttonLanjutkanDeskripsiBarang;
    EditText buttondeskripsiedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_barang);

        //set font
        tv1=(TextView)findViewById(R.id.teks_1_deskripsibarang);
        tv2=(TextView)findViewById(R.id.teks_2_deskripsibarang);
        tv3=(TextView)findViewById(R.id.teks_3_deskripsibarang);
        tv4=(TextView)findViewById(R.id.teks_4_deskripsibarang);
        tv5=(TextView)findViewById(R.id.teks_5_deskripsibarang);

        Typeface face= Typeface.createFromAsset(getAssets(), "font/Roboto-Thin.ttf");
        tv1.setTypeface(face);

        Typeface face1= Typeface.createFromAsset(getAssets(), "font/Roboto-Bold.ttf");
        tv2.setTypeface(face1);

        Typeface face2= Typeface.createFromAsset(getAssets(), "font/Roboto-Regular.ttf");
        tv3.setTypeface(face2);

        Typeface face3= Typeface.createFromAsset(getAssets(), "font/Roboto-Bold.ttf");
        tv4.setTypeface(face3);

        Typeface face4= Typeface.createFromAsset(getAssets(), "font/Roboto-Regular.ttf");
        tv5.setTypeface(face4);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_deskripsiActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        //EditText Deskripsi
        buttondeskripsiedit = (EditText) findViewById(R.id.editText_deskripsibarang);

        buttondeskripsiedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditTextDeskripsiActivity.class);
                startActivity(intent);
            }
        });

        //button close
        buttonCloseDeskrispiBarang = (ImageButton) findViewById(R.id.close_deskripsibarang_button);

        buttonCloseDeskrispiBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        //button lanjutkan
        buttonLanjutkanDeskripsiBarang = (Button) findViewById(R.id.lanjutkan_deskrispibarang_button);

        buttonLanjutkanDeskripsiBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailBarangActivity.class);
                startActivity(intent);
            }
        });
    }
}
