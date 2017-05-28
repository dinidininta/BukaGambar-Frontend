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

import com.example.bukagambarfrontend.POJO.Provinces;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GratisBiayaKirimActivity extends AppCompatActivity {

    ImageButton buttonbackPengiriman;
    Button buttonsimpanpengiriman;
    String[] list_daerah;
    ListView listView;
    public GratisBiayaListAdapter adapter;
    public static String gratiskirim = "";

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
                adapter = new GratisBiayaListAdapter(GratisBiayaKirimActivity.this, provinces.getProvinces());
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static class GratisBiayaListAdapter extends BaseAdapter {

        List<String> daerah;
        Context context;
        private static LayoutInflater inflater = null;

        public GratisBiayaListAdapter(Context mContext, List<String> mPro) {
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

        public class Holder {
            TextView atribut_list_daerah;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            Holder holder = new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.list_step_gratisbiayakirim, null);
            holder.atribut_list_daerah = (TextView) rowView.findViewById(R.id.atribut_list_gratisbiaya);
            holder.atribut_list_daerah.setText(daerah.get(position));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You Clicked" + daerah.get(position), Toast.LENGTH_LONG).show();
                    gratiskirim = gratiskirim + ", " + daerah.get(position);
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
