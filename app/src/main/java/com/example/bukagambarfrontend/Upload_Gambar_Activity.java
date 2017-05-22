package com.example.bukagambarfrontend;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class Upload_Gambar_Activity extends AppCompatActivity {

    ArrayList<String> filePaths;
    Bitmap bitmap;
    ImageView[] gambarProduk = new ImageView[5];
    TextView[] size = new TextView[5];
    GridView gridView;
    ImageButton buttonCloseUploadGambar;
    Button buttonSimpanUploadGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__gambar_);

        gambarProduk[0] = (ImageView) findViewById(R.id.image0);
        gambarProduk[1] = (ImageView) findViewById(R.id.image1);
        gambarProduk[2] = (ImageView) findViewById(R.id.image2);
        gambarProduk[3] = (ImageView) findViewById(R.id.image3);
        gambarProduk[4] = (ImageView) findViewById(R.id.image4);
//        gridView = (GridView) findViewById(R.id.grid_view);
        size[0] = (TextView) findViewById(R.id.size0);
        size[1] = (TextView) findViewById(R.id.size1);
        size[2] = (TextView) findViewById(R.id.size2);
        size[3] = (TextView) findViewById(R.id.size3);
        size[4] = (TextView) findViewById(R.id.size4);


        buttonCloseUploadGambar = (ImageButton) findViewById(R.id.close_uploadgambar_button);

        buttonSimpanUploadGambar = (Button) findViewById(R.id.simpan_uploadgambar_button);

        buttonCloseUploadGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        buttonSimpanUploadGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case FilePickerConst.REQUEST_CODE:
                if(resultCode==RESULT_OK && data!=null)
                {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    filePaths = new ArrayList<>();
                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    //use them anywhere
                    String toShow = "";
                    for (int i=0; i<filePaths.size(); i++){
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(filePaths.get(i), options);
                        int imageHeight = options.outHeight;
                        int imageWidth = options.outWidth;
                        if(imageHeight < 300 || imageWidth < 300){
                            toShow = toShow + "\n" + "Resolusi gambar minimal 300 x 300";
                            size[i].setText(String.valueOf(imageHeight) + " x " + String.valueOf(imageWidth));
                        }else{
                            toShow = toShow + "\n" + filePaths.get(i);
                            size[i].setText(String.valueOf(imageHeight) + " x " + String.valueOf(imageWidth));
                            options.inJustDecodeBounds = false;
                            gambarProduk[i].setImageBitmap(BitmapFactory.decodeFile(filePaths.get(i), options));
                        }
                    }

                    new AlertDialog.Builder(Upload_Gambar_Activity.this).setTitle("Berhasil")
                            .setMessage(toShow)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            }).show();
                }
        }
    }

    public void uploadOnClick(View view) {
        FilePickerBuilder.getInstance().setMaxCount(5)
                .setSelectedFiles(filePaths)
                .setActivityTheme(R.style.FilePickerTheme)
                .pickPhoto(this);
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
