package com.example.bukagambarfrontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PengirimanBarangActivity extends AppCompatActivity {

    Button simpanpengiriman;
    ImageButton closepengiriman;
    TextView gratistextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengiriman_barang);

        //Instansiasi Toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_pengirimanActivity);
        //button gratis pengiriman
        gratistextbutton = (TextView) findViewById(R.id.textView);
        //button close
        closepengiriman = (ImageButton) findViewById(R.id.close_pengiriman_button);
        //button simpan
        simpanpengiriman = (Button) findViewById(R.id.simpan_pengiriman_button);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");


        gratistextbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GratisBiayaKirimActivity.class);
                startActivity(intent);
            }
        });



       closepengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });



       simpanpengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });
}
    }