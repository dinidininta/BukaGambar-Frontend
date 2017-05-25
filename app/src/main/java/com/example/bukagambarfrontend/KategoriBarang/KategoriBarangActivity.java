package com.example.bukagambarfrontend.KategoriBarang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukagambarfrontend.APIService;
import com.example.bukagambarfrontend.DeskripsiBarangActivity;
import com.example.bukagambarfrontend.NamaBarangActivity;
import com.example.bukagambarfrontend.POJO.Category;
import com.example.bukagambarfrontend.POJO.Child;
import com.example.bukagambarfrontend.POJO.RootObject;
import com.example.bukagambarfrontend.R;
import com.example.bukagambarfrontend.StepSatuActivity;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KategoriBarangActivity extends AppCompatActivity {

    ImageButton buttoncloseKategoriBarang;
    Button buttonLanjutkanKategoriBarang;
    String[] list_kategori;
    ListView listView;
    public KategoriBarangListAdapter adapter;
    public static String kategori_barang = "";
    public static int id_kategori_barang = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_barang);
        list_kategori = getResources().getStringArray(R.array.daftar_kategori);
        listView = (ListView) findViewById(R.id.list_of_kategori_saran);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_kategoriActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        String nb = NamaBarangActivity.nama_barang;
        Toast.makeText(this, nb, Toast.LENGTH_SHORT).show();


        //button close
        buttoncloseKategoriBarang = (ImageButton) findViewById(R.id.close_kategoribarang_button);
        buttoncloseKategoriBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        //button lanjutkan
        buttonLanjutkanKategoriBarang = (Button) findViewById(R.id.lanjutkan_kategoribarang_button);
        buttonLanjutkanKategoriBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeskripsiBarangActivity.class);
                startActivity(intent);
            }
        });

        getCategory();
    }

    private void getCategory(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.bukalapak.com/v2")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        APIService apiService = restAdapter.create(APIService.class);
        apiService.getCategory(new Callback<RootObject>() {
            @Override
            public void success(RootObject rootObject, Response response) {
                adapter = new KategoriBarangListAdapter(KategoriBarangActivity.this, rootObject.getCategories());
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static class KategoriBarangListAdapter extends ArrayAdapter<Category> {

        public KategoriBarangListAdapter(Context mContext, ArrayList<Category> mCat) {
            super(mContext, 0, mCat);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Category category = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_step_kategori, parent, false);
            }
            TextView atribut_list_kategori = (TextView) convertView.findViewById(R.id.atribut_list_kategori);
            atribut_list_kategori.setText(category.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "You Clicked " + category.getName() + " " + position, Toast.LENGTH_LONG).show();
                    kategori_barang = category.getName();
                    id_kategori_barang = position;
                    Intent intent = new Intent(getContext(), SubKategoriActivity.class);
                    getContext().startActivity(intent);
//                    switch (position) {
//                        case 0:
//                            Intent intent = new Intent(context, StepSatuActivity.class);
//                            context.startActivity(intent);
//
//                    }

                }
            });
            return convertView;

        }
    }
}