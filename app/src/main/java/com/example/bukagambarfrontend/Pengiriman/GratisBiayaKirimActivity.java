package com.example.bukagambarfrontend.Pengiriman;

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

import com.example.bukagambarfrontend.APIService;
import com.example.bukagambarfrontend.POJO.ProvincesPOJO.Provinces;
import com.example.bukagambarfrontend.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GratisBiayaKirimActivity extends AppCompatActivity {

    ImageButton buttonbackPengiriman;
    Button buttonsimpanpengiriman;
    ListView listView;
    public GratisBiayaListAdapter adapter;
    public static List<String> gratiskirim = new ArrayList<>();
    public static List<Integer> code =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gratis_biaya_kirim);

        //Instansiasi Toolbar
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_gratisbiayaActivity);
        //Instansiasi Tittle Toolbar
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_tittle);
        //Instansiasi button back
        buttonbackPengiriman = (ImageButton) findViewById(R.id.back_gratisbiaya_button);
        //Instansiasi button simpan
        buttonsimpanpengiriman = (Button) findViewById(R.id.simpan_gratisbiaya_button);
        //Instansiasi ListView
        listView = (ListView) findViewById(R.id.list_of_gratisbiaya);

        buttonbackPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PengirimanBarangActivity.class);
                startActivity(intent);
            }
        });


        buttonsimpanpengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PengirimanBarangActivity.class);
                startActivity(intent);
            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");


//        list_daerah = getResources().getStringArray(R.array.daftar_daerah);
        getProvinces();
    }

    private void getProvinces(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.bukalapak.com/v2")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        APIService apiService = restAdapter.create(APIService.class);
        apiService.getProvinces(new Callback<Provinces>() {
            @Override
            public void success(Provinces provinces, Response response) {
                List<String> area = provinces.getProvinces();
                area.add(0, "Seluruh Indonesia");
                area.add(1, "Pulau Jawa");
                area.add(2, "Jabodetabek");
                adapter = new GratisBiayaListAdapter(GratisBiayaKirimActivity.this, area);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    class GratisBiayaListAdapter extends BaseAdapter {

        List<String> daerah;
        Context context;
        private LayoutInflater inflater = null;

        GratisBiayaListAdapter(Context mContext, List<String> mPro) {
            daerah = mPro;
            context = mContext;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return daerah.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class Holder {
            TextView atribut_list_daerah;
            ImageButton chevron_free;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.list_step_gratisbiayakirim, null);
            holder.atribut_list_daerah = (TextView) rowView.findViewById(R.id.atribut_list_gratisbiaya);
            holder.chevron_free = (ImageButton) rowView.findViewById(R.id.chevron_free_cities);
            holder.atribut_list_daerah.setText(daerah.get(position));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You Clicked" + daerah.get(position), Toast.LENGTH_LONG).show();
                    gratiskirim.add(daerah.get(position));
                    holder.chevron_free.setVisibility(View.VISIBLE);
                    code.add(position);
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
