package com.example.bukagambarfrontend.KategoriBarang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.bukagambarfrontend.POJO.Category_.Child;
import com.example.bukagambarfrontend.POJO.Category_.RootObject;
import com.example.bukagambarfrontend.R;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by WIN8 on 5/22/2017.
 */

public class SubKategoriActivity extends AppCompatActivity {

    public static String sub_kat_barang = "";
    public static String id_kat = "";
    Button buttonSimpanSubkat;
    ImageButton backSubkat;
    ListView listView;
    public SubKategoriBarangListAdapter adapter;
    TextView toolbarTitle;

    public void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);
        setContentView(R.layout.activity_sub_kategori);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_subKategori);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        buttonSimpanSubkat = (Button) findViewById(R.id.simpan_subkat_button);
        backSubkat = (ImageButton) findViewById(R.id.close_subkat_button);
        listView = (ListView) findViewById(R.id.list_of_sub_kategori);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title_dynamic);

        toolbarTitle.setText(KategoriBarangActivity.kategori_barang);

        buttonSimpanSubkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KategoriBarangActivity.class);
                startActivity(intent);
            }
        });

        backSubkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KategoriBarangActivity.class);
                startActivity(intent);
            }
        });

        getSubCategory();

    }

    public void getSubCategory(){
        final int id = KategoriBarangActivity.id_kategori_barang;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.bukalapak.com/v2")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        final APIService apiService = restAdapter.create(APIService.class);
        apiService.getCategory(new Callback<RootObject>() {
            @Override
            public void success(RootObject rootObject, Response response) {
                adapter = new SubKategoriBarangListAdapter(getApplicationContext(), rootObject.getCategories().get(id).getChildren());
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



    public static class SubKategoriBarangListAdapter extends ArrayAdapter<Child> {

        SubKategoriBarangListAdapter(Context mContext, ArrayList<Child> mCatChild) {
            super(mContext, 0, mCatChild);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Child child = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_step_kategori, parent, false);
            }
            TextView atribut_list_kategori = (TextView) convertView.findViewById(R.id.atribut_list_kategori);
            atribut_list_kategori.setText(child.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "You Clicked" + child.getName(), Toast.LENGTH_LONG).show();
                    sub_kat_barang = " / "+child.getName();
                    id_kat = String.valueOf(child.getId());
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
