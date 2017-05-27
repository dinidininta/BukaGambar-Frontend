package com.example.bukagambarfrontend;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukagambarfrontend.DeskripsiBarang.DeskripsiBarangActivity;
import com.example.bukagambarfrontend.DeskripsiBarang.EditTextDeskripsiActivity;
import com.example.bukagambarfrontend.DetailBarang.DetailBarangActivity;
import com.example.bukagambarfrontend.Generator.CreateProductGenerator;
import com.example.bukagambarfrontend.KategoriBarang.KategoriBarangActivity;
import com.example.bukagambarfrontend.KategoriBarang.SubKategoriActivity;
import com.example.bukagambarfrontend.POJO.ProductResponsePOJO.ProductResponse;
import com.example.bukagambarfrontend.POJO.ProductsPOJO.Product;
import com.example.bukagambarfrontend.POJO.ProductsPOJO.ProductDetailAttributes;
import com.example.bukagambarfrontend.POJO.ProductsPOJO.ProductJson;
import com.example.bukagambarfrontend.Pengiriman.GratisBiayaKirimActivity;
import com.example.bukagambarfrontend.Pengiriman.PengirimanBarangActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class StepSatuActivity extends AppCompatActivity {

    Button simpanDrafButton;
    ImageButton closeButton;
    Button lanjutkanButton;
    Button jualButton;
    Button tambahGambarButton;
    ImageView[] previewGambarProduk = new ImageView[5];
    String userId = "31615831";
    String token = "qIWIiAyCESmfAJU25UK";

    List<String> judul = new ArrayList<>();
    List<String> keterangan = new ArrayList<>();
    ArrayList<String> paths;
    ListView listView;
    String namabarang, kategoribarang, deskripsibarang, detailbarang, pengirimanbarang, hargabarang, stokbarang, beratbarang, statusbarang;
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
        final Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_stepsatu);
        //Instansiasi ListView
        listView = (ListView) findViewById(R.id.list_of_step_satu);

        previewGambarProduk[0] = (ImageView) findViewById(R.id.image_1_konten);
        previewGambarProduk[1] = (ImageView) findViewById(R.id.image_2_konten);
        previewGambarProduk[2] = (ImageView) findViewById(R.id.image_3_konten);
        previewGambarProduk[3] = (ImageView) findViewById(R.id.image_4_konten);
        previewGambarProduk[4] = (ImageView) findViewById(R.id.image_5_konten);


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

        paths = Upload_Gambar_Activity.filePaths;
        if(!paths.isEmpty()){
            for(int i=0; i<paths.size(); i++){
                previewGambarProduk[i].setImageBitmap(decodeSampledBitmapFromResource(paths.get(i), 300, 300));
            }
        }

        namabarang = NamaBarangActivity.nama_barang;
        if(namabarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_nama_barang));
        }else {
            keterangan.add(namabarang);
        }

        kategoribarang = KategoriBarangActivity.kategori_barang + SubKategoriActivity.sub_kat_barang;
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
        hargabarang = DetailBarangActivity.hargabarang;
        beratbarang = DetailBarangActivity.beratbarang;
        stokbarang = DetailBarangActivity.stokbarang;
        statusbarang = DetailBarangActivity.status;
        if(hargabarang.isEmpty() && beratbarang.isEmpty() && stokbarang.isEmpty() && statusbarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_detail_barang));
        }else {
            keterangan.add(beratbarang + ", " + stokbarang + ", " + hargabarang + ", " + statusbarang);
        }

        pengirimanbarang = PengirimanBarangActivity.pengiriman + GratisBiayaKirimActivity.gratiskirim;
        if(pengirimanbarang.isEmpty()){
            keterangan.add(res.getString(R.string.ket_pengiriman));
        }else {
            keterangan.add(pengirimanbarang);
        }

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

        jualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> free = new ArrayList<Integer>();
                free.add(2);
                free.add(3);
                free.add(4);
                ProductDetailAttributes attr = new ProductDetailAttributes();
                attr.setBrand("Asus");
                Product newProd = new Product();
                newProd.setName(namabarang);
                newProd.setDescriptionBb(deskripsibarang);
                newProd.setCategoryId("1513");
                newProd.setNegotiable("true");
                newProd.setPrice("125000");
                newProd.setNew(String.valueOf(DetailBarangActivity.stat));
                newProd.setStock("2");
                newProd.setWeight("12");
                newProd.setProductDetailAttributes(attr);
                newProd.setFreeShipping(free);
                final ProductJson productJson = new ProductJson();
                productJson.setProduct(newProd);
                productJson.setImages("1259879712");
                productJson.setForceInsurance("");
                APIService service = CreateProductGenerator.createService(APIService.class, userId, token);
                service.uploadProduk(productJson, new Callback<ProductResponse>() {
                    @Override
                    public void success(ProductResponse productResponse, Response response) {
                        Toast.makeText(getApplicationContext(), productResponse.getStatus() + "\n" + productResponse.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
//                FragmentManager fm = getSupportFragmentManager();
//                DialogSukses dialogSukses = new DialogSukses();
//                dialogSukses.show(fm, "Berhasil");
//                new AlertDialog.Builder(StepSatuActivity.this)
//                        .setTitle("Gagal")
//                        .setMessage("Foto kamu teridentifikasi milik orang lain")
//                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        }).show();
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

    public static Bitmap decodeSampledBitmapFromResource(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
