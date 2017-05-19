package com.example.bukagambarfrontend;

import android.content.Context;
import android.content.Intent;
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

public class KategoriBarangActivity extends AppCompatActivity {

    ImageButton buttoncloseKategoriBarang;
    Button buttonLanjutkanKategoriBarang;
    String[] list_kategori;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_barang);
        list_kategori = getResources().getStringArray(R.array.daftar_kategori);
        listView = (ListView) findViewById(R.id.list_of_kategori_saran);
        KategoriBarangListAdapter adapter = new KategoriBarangListAdapter(this, list_kategori);
        listView.setAdapter(adapter);

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_kategoriActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

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
    }

    public static class KategoriBarangListAdapter extends BaseAdapter {

        String[] kategori;
        Context context;
        private static LayoutInflater inflater = null;

        public KategoriBarangListAdapter(Context mContext, String[] mCat) {
            kategori = mCat;
            context = mContext;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return kategori.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder {
            TextView atribut_list_kategori;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.list_step_kategori, null);
            holder.atribut_list_kategori = (TextView) rowView.findViewById(R.id.atribut_list_kategori);
            holder.atribut_list_kategori.setText(kategori[position]);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You Clicked" + kategori[position], Toast.LENGTH_LONG).show();

//                    switch (position) {
//                        case 0:
//                            Intent intent = new Intent(context, StepSatuActivity.class);
//                            context.startActivity(intent);
//
//                    }

                }
            });
            return rowView;

        }
    }
}
