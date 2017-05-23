package com.example.bukagambarfrontend;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DetailBarangActivity extends AppCompatActivity {

    Button buttonlanjutkanDetailBarang;
    ImageButton buttoncloseDetailBarang;
    EditText etgram, etbuah;
    SuffixTextDrawable gram, buah;
    PrefixEditText etsatuan;
    public static String detailbarang = "";
    String beratbarang="", stokbarang="", hargabarang="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        //instansiasi Toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_detailActivity);
        //instansiasi button lanjutkan
        buttonlanjutkanDetailBarang = (Button) findViewById(R.id.lanjutkan_detailbarang_button);
        //instansiasi button close
        buttoncloseDetailBarang = (ImageButton) findViewById(R.id.close_detailbarang_button);
        //instansiasi Gram
        etgram = (EditText) findViewById(R.id.editText_gram);
        //instansiasi Buah
        etbuah = (EditText) findViewById(R.id.editText_buah);
        //instansiasi Satuan
        etsatuan = (PrefixEditText) findViewById(R.id.editText_satuan);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

       buttonlanjutkanDetailBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PengirimanBarangActivity.class);
                startActivity(intent);
            }
        });


        buttoncloseDetailBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        //suffix gram
        gram = new SuffixTextDrawable("Gram");
        etgram.setCompoundDrawablesWithIntrinsicBounds(null, null, gram, null);

        try{
            beratbarang = etgram.getText().toString() + "g, ";
        }catch (NullPointerException e){
            beratbarang = "";
        }

        //suffix buah
        buah = new SuffixTextDrawable("Buah");
        etbuah.setCompoundDrawablesWithIntrinsicBounds(null, null, buah, null);

        try{
            stokbarang = etbuah.getText().toString() + " Stok, ";
        }catch (NullPointerException e){
            stokbarang = "";
        }

        //prefix rupiah
//        rupiah = new PrefixTextDrawable("Rp");
        //etsatuan.setCompoundDrawablesWithIntrinsicBounds(rupiah, null, null, null);
//        etsatuan.setCompoundDrawablesRelative(rupiah, null, null, null);

        try{
            hargabarang = "Rp. " + etsatuan.getText().toString() + ",-";
        }catch (NullPointerException e){
            hargabarang = "";
        }

        detailbarang = beratbarang + stokbarang + hargabarang;
    }

    private static class SuffixTextDrawable extends Drawable {

        private final String text;
        private final Paint paint;

        SuffixTextDrawable(String text) {

            this.text = text;

            this.paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(40f);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.RIGHT);
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            canvas.drawText(text, 0, 0, paint);
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }

}
