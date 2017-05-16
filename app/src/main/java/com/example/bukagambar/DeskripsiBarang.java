package com.example.bukagambar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DeskripsiBarang extends AppCompatActivity {

    ImageButton buttonCloseDeskrispiBarang;
    Button buttonLanjutkanDeskripsiBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_barang);

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
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });
    }
}
