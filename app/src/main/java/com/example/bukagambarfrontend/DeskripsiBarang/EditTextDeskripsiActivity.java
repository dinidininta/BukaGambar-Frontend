package com.example.bukagambarfrontend.DeskripsiBarang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.bukagambarfrontend.R;

public class EditTextDeskripsiActivity extends AppCompatActivity {

    EditText et;
    ImageButton buttonbackedittextDeskripsi;
    Button buttonsimpanedittextDeskripsi;
    public static String desc_barang = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_deskripsi);

        // Get the widgets reference from XML layout
        et = (EditText) findViewById(R.id.deskripsibarang_form);

        if(!desc_barang.isEmpty()){
            et.setText(desc_barang);
        }

        //button back
        buttonbackedittextDeskripsi = (ImageButton) findViewById(R.id.back_edittextDeskripsi_button);
        //button simpan
        buttonsimpanedittextDeskripsi = (Button) findViewById(R.id.simpan_edittextDeskripsi_button);


       buttonbackedittextDeskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeskripsiBarangActivity.class);
                startActivity(intent);
            }
        });


        buttonsimpanedittextDeskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et.getText().toString().length() < 30){
                    new AlertDialog.Builder(EditTextDeskripsiActivity.this)
                            .setMessage("Deskripsi minimal 30 karakter")
                            .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }else {
                    desc_barang = et.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), DeskripsiBarangActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
