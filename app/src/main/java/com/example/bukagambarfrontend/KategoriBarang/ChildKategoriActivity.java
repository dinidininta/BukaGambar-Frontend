package com.example.bukagambarfrontend.KategoriBarang;

import android.content.Context;
import android.content.Intent;
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
import com.example.bukagambarfrontend.POJO.CategorPOJO.Child;
import com.example.bukagambarfrontend.POJO.CategorPOJO.Child2;
import com.example.bukagambarfrontend.POJO.CategorPOJO.RootObject;
import com.example.bukagambarfrontend.R;
import com.example.bukagambarfrontend.ServiceGenerator.BukalapakGenerator;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChildKategoriActivity extends AppCompatActivity {

    public static String child_kat_barang = "";
    public static int id_child_barang = 0;
    ChildKategoriListAdapter adapter;
    Button simpanCKat;
    ImageButton closeCKat;
    ListView listview;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_kategori);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_childKategori);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        simpanCKat = (Button) findViewById(R.id.simpan_ckat_button);
        closeCKat = (ImageButton) findViewById(R.id.close_ckat_button);
        listview = (ListView) findViewById(R.id.list_of_child_kategori);
        title = (TextView) findViewById(R.id.toolbar_title_child);

        title.setText(SubKategoriActivity.sub_kat_barang);

        simpanCKat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubKategoriActivity.class);
                startActivity(intent);
            }
        });

        closeCKat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubKategoriActivity.class);
                startActivity(intent);
            }
        });

        getChildCategory(KategoriBarangActivity.id_kategori_barang, SubKategoriActivity.identifier);
    }

    private void getChildCategory(final int mainId, final int subId){
        APIService apiService = BukalapakGenerator.createService(APIService.class);
        apiService.getCategory(new Callback<RootObject>() {
            @Override
            public void success(RootObject rootObject, Response response) {
                adapter = new ChildKategoriListAdapter(getApplicationContext(), rootObject.getCategories().get(mainId).getChildren().get(subId).getChildren());
                listview.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static class ChildKategoriListAdapter extends ArrayAdapter<Child2> {

        ChildKategoriListAdapter(Context mContext, ArrayList<Child2> mCatChild) {
            super(mContext, 0, mCatChild);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Child2 child2 = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_child2_kategori, parent, false);
            }
            TextView atribut_list_kategori = (TextView) convertView.findViewById(R.id.atribut_child_kategori);
            atribut_list_kategori.setText(child2.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "You Clicked" + child2.getName(), Toast.LENGTH_LONG).show();
                    child_kat_barang = child2.getName();
                    id_child_barang = child2.getId();
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
