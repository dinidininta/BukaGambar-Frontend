package com.example.bukagambarfrontend;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bukagambarfrontend.KategoriBarang.KategoriBarangActivity;

import java.util.ArrayList;
import java.util.List;

public class StepSatuActivity extends AppCompatActivity {

    Button simpanDrafButton;
    ImageButton closeButton;
    Button lanjutkanButton;
    Button jualButton;
    Button tambahGambarButton;

    List<String> judul = new ArrayList<>();
    List<String> keterangan = new ArrayList<>();
    ListView listView;
    String namabarang, kategoribarang, deskripsibarang, detailbarang;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_satu);

        //Instansiasi button simpan draf
        simpanDrafButton = (Button) findViewById(R.id.simpan_draf_Button);
        //Instanisiasi button tambah gambar
        tambahGambarButton = (Button) findViewById(R.id.tambahgambar_button);
        //Instansiasi button jual
        jualButton = (Button) findViewById(R.id.jual_Button);
        //Instansiasi button exit
        closeButton = (ImageButton) findViewById(R.id.close_button);
        //Instansiasi button lanjutkan
        lanjutkanButton = (Button) findViewById(R.id.lanjutkan_1_button);
        //Instansiasi Toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_stepsatu);
        //Instansiasi ListView
        listView = (ListView) findViewById(R.id.list_of_step_satu);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        //toolbar.setLogo(R.drawable.ic_toolbar);
        StepSatuListAdapter adapter = new StepSatuListAdapter(this, judul, keterangan);
        listView.setAdapter(adapter);


//        simpanDrafButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Upload_Gambar_Activity.class);
//                startActivity(intent);
//            }
//        });


        res = getResources();

        judul.add(res.getString(R.string.nama_barang));
        judul.add(res.getString(R.string.kategori_barang));
        judul.add(res.getString(R.string.deskripsi_barang));
        judul.add(res.getString(R.string.detail_barang));
        judul.add(res.getString(R.string.pengiriman));
        judul.add(res.getString(R.string.harga_barang));
        judul.add(res.getString(R.string.kualitas_barang));

        namabarang = NamaBarangActivity.nama_barang;
        if(namabarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_nama_barang));
        }else {
            keterangan.add(namabarang);
        }

        kategoribarang = KategoriBarangActivity.kategori_barang;
        if(kategoribarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_kategori_barang));
        }else {
            keterangan.add(kategoribarang);
        }

        deskripsibarang = EditTextDeskripsiActivity.desc_barang;
        if(deskripsibarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_deskripsi_barang));
        }else {
            keterangan.add(deskripsibarang);
        }

        detailbarang = DetailBarangActivity.detailbarang;
        if(detailbarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_detail_barang));
        }else {
            keterangan.add(detailbarang);
        }

        keterangan.add(res.getString(R.string.ket_pengiriman));
        keterangan.add(res.getString(R.string.ket_harga_barang));
        keterangan.add(res.getString(R.string.ket_kul_barang));

        tambahGambarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Upload_Gambar_Activity.class);
                startActivity(intent);
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


        lanjutkanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NamaBarangActivity.class);
                startActivity(intent);
            }
        });

    }

    public static class StepSatuListAdapter extends BaseAdapter{

        List<String> judul_step;
        List<String> ket_step;
        Context context;
        private static LayoutInflater inflater=null;

        public StepSatuListAdapter(Context mContext, List<String> mJudul, List<String> mKet){
            judul_step = mJudul;
            ket_step = mKet;
            context = mContext;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount(){
            return judul_step.size();
        }

        @Override
        public Object getItem(int position){
            return position;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        public class Holder{
            TextView judul_atribut;
            TextView detail_atribut;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.list_step_satu_template, null);
            holder.judul_atribut = (TextView) rowView.findViewById(R.id.judul_atribut);
            holder.detail_atribut = (TextView) rowView.findViewById(R.id.detail_atribut);
            holder.judul_atribut.setText(judul_step.get(position));
            holder.detail_atribut.setText(ket_step.get(position));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String identifier = judul_step.get(position);
                    Toast.makeText(context, "You Clicked" + identifier, Toast.LENGTH_LONG).show();
                    Intent intent;
                    if(identifier.equals(context.getString(R.string.nama_barang))){
                        intent = new Intent(context, NamaBarangActivity.class);
                        context.startActivity(intent);
                    }else if(identifier.equals(context.getString(R.string.kategori_barang))){
                        intent = new Intent(context, KategoriBarangActivity.class);
                        context.startActivity(intent);
                    }else if(identifier.equals(context.getString(R.string.deskripsi_barang))){
                        intent = new Intent(context, DeskripsiBarangActivity.class);
                        context.startActivity(intent);
                    }else if(identifier.equals(context.getString(R.string.detail_barang))){
                        intent = new Intent(context, DetailBarangActivity.class);
                        context.startActivity(intent);
                    }else if(identifier.equals(context.getString(R.string.pengiriman))){
                        intent = new Intent(context, PengirimanBarangActivity.class);
                        context.startActivity(intent);
                    }else {
                        intent = new Intent(context, NamaBarangActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
            return  rowView;

        }
    }
}
