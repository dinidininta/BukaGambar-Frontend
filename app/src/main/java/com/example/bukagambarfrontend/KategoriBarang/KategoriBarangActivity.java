package com.example.bukagambarfrontend.KategoriBarang;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukagambarfrontend.APIService;
import com.example.bukagambarfrontend.DeskripsiBarang.DeskripsiBarangActivity;
import com.example.bukagambarfrontend.NamaBarangActivity;
import com.example.bukagambarfrontend.POJO.CategorPOJO.Category;
import com.example.bukagambarfrontend.POJO.CategorPOJO.RootObject;
import com.example.bukagambarfrontend.R;
import com.example.bukagambarfrontend.ServiceGenerator.BukalapakGenerator;
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
        APIService apiService = BukalapakGenerator.createService(APIService.class);
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

    class KategoriBarangListAdapter extends ArrayAdapter<Category> {

        KategoriBarangListAdapter(Context mContext, ArrayList<Category> mCat) {
            super(mContext, 0, mCat);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Category category = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_step_kategori, parent, false);
            }
            TextView atribut_list_kategori = (TextView) convertView.findViewById(R.id.atribut_list_kategori);
            final ImageButton chevron_list = (ImageButton) convertView.findViewById(R.id.chevron_step_kategori);
            atribut_list_kategori.setText(category.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kategori_barang = category.getName();
                    id_kategori_barang = position;
                    chevron_list.setImageResource(R.drawable.ic_check_black_24dp);
                    Intent intent = new Intent(KategoriBarangActivity.this, SubKategoriActivity.class);
                    KategoriBarangActivity.this.startActivity(intent);

                }
            });
            return convertView;

        }
    }
}
