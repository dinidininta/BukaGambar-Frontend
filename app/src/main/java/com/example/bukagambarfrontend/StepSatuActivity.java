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

public class StepSatuActivity extends AppCompatActivity {

    Button simpanButton;
    ImageButton closeButton;
    Button lanjutkanButton;

    int[] judul = {R.string.nama_barang, R.string.kategori_barang, R.string.deskripsi_barang, R.string.detail_barang, R.string.pengiriman, R.string.harga_barang, R.string.kualitas_barang};
    int[] keterangan = {R.string.ket_nama_barang, R.string.ket_kategori_barang, R.string.ket_deskripsi_barang, R.string.ket_detail_barang, R.string.ket_pengiriman, R.string.ket_harga_barang, R.string.ket_kul_barang};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_satu);

        //button simpan
        simpanButton = (Button) findViewById(R.id.saveButton);
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Test_Gallery_Activity.class);
                startActivity(intent);
            }
        });

        //button exit
        closeButton = (ImageButton) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        //button lanjutkan
        lanjutkanButton = (Button) findViewById(R.id.lanjutkan_1_button);
        lanjutkanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NamaBarangActivity.class);
                startActivity(intent);
            }
        });

        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_stepsatu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        //toolbar.setLogo(R.drawable.ic_toolbar);
        listView = (ListView) findViewById(R.id.list_of_step_satu);

        StepSatuListAdapter adapter = new StepSatuListAdapter(this, judul, keterangan);
        listView.setAdapter(adapter);
    }

    public static class StepSatuListAdapter extends BaseAdapter{

        int[] judul_step;
        int[] ket_step;
        Context context;
        private static LayoutInflater inflater=null;

        public StepSatuListAdapter(Context mContext, int[] mJudul, int[] mKet){
            judul_step = mJudul;
            ket_step = mKet;
            context = mContext;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount(){
            return judul_step.length;
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
            Intent intent = new Intent();
            View rowView;
            rowView = inflater.inflate(R.layout.list_step_satu_template, null);
            holder.judul_atribut = (TextView) rowView.findViewById(R.id.judul_atribut);
            holder.detail_atribut = (TextView) rowView.findViewById(R.id.detail_atribut);
            holder.judul_atribut.setText(judul_step[position]);
            holder.detail_atribut.setText(ket_step[position]);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You Clicked" + judul_step[position], Toast.LENGTH_LONG).show();
                    Intent intent;
                    switch (judul_step[position]) {
                        case 2131099712:
                            intent = new Intent(context, NamaBarangActivity.class);
                            context.startActivity(intent);
                            break;
                        case 2131099701:
                            intent = new Intent(context, KategoriBarangActivity.class);
                            context.startActivity(intent);
                            break;
                        case 2131099694:
                            intent = new Intent(context, DeskripsiBarangActivity.class);
                            context.startActivity(intent);
                            break;
                        case 2131099695:
                            intent = new Intent(context, DetailBarangActivity.class);
                            context.startActivity(intent);
                            break;
                        case 2131099713:
                            intent = new Intent(context, PengirimanBarangActivity.class);
                            context.startActivity(intent);
                            break;
                        default:
                            intent = new Intent(context, NamaBarangActivity.class);
                            context.startActivity(intent);
                            break;
                    }

                }
            });
return  rowView;

        }
    }
}
