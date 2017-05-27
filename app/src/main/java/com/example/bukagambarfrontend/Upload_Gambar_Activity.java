package com.example.bukagambarfrontend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bukagambarfrontend.DialogFragments.DialogAccept;
import com.example.bukagambarfrontend.Generator.ServiceGenerator;
import com.example.bukagambarfrontend.POJO.ImagePOJO.ImageResponse;

import java.io.File;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class Upload_Gambar_Activity extends AppCompatActivity {

    public static ArrayList<String> filePaths = new ArrayList<>(); ;
    Bitmap bitmap;
    ImageView[] gambarProduk = new ImageView[5];
    //TextView[] size = new TextView[5];
    GridView gridView;
    ImageButton buttonCloseUploadGambar;
    Button buttonSimpanUploadGambar;
    String API_BASE_URL = "http://bshare.id/inginwisuda";
    String userId = "31616631";
    String token = "BRybSh10q4tRd7K1p8ll";
    //ImageView rejectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__gambar_);

        gambarProduk[0] = (ImageView) findViewById(R.id.image0);
        gambarProduk[1] = (ImageView) findViewById(R.id.image1);
        gambarProduk[2] = (ImageView) findViewById(R.id.image2);
        gambarProduk[3] = (ImageView) findViewById(R.id.image3);
        gambarProduk[4] = (ImageView) findViewById(R.id.image4);

        //rejectedImage = (ImageView) findViewById(R.id.image_dialogrejected);
//        gridView = (GridView) findViewById(R.id.grid_view);
//        size[0] = (TextView) findViewById(R.id.size0);
//        size[1] = (TextView) findViewById(R.id.size1);
//        size[2] = (TextView) findViewById(R.id.size2);
//        size[3] = (TextView) findViewById(R.id.size3);
//        size[4] = (TextView) findViewById(R.id.size4);


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
                            //size[i].setText(String.valueOf(imageHeight) + " x " + String.valueOf(imageWidth));
                        }else{
                            toShow = toShow + "\n" + filePaths.get(i);
                            //size[i].setText(String.valueOf(imageHeight) + " x " + String.valueOf(imageWidth));
                            options.inJustDecodeBounds = false;
                            gambarProduk[i].setImageBitmap(decodeSampledBitmapFromResource(filePaths.get(i), 300, 300));
                            //uploadToServer(filePaths.get(i));
                        }
                    }

                    uploadToServer(filePaths.get(0));

                    FragmentManager fm = getSupportFragmentManager();
                    DialogAccept dialogAccept = new DialogAccept();
                    dialogAccept.show(fm, "Berhasil");

//                    AlertDialog.Builder builder = new AlertDialog.Builder(Upload_Gambar_Activity.this);
//                    LayoutInflater inflater = Upload_Gambar_Activity.this.getLayoutInflater();
//                    View dialogView = inflater.inflate(R.layout.dialog_rejected, null);
//                    builder.setView(dialogView)
//                            .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            }).create().show();


//                    new AlertDialog.Builder(Upload_Gambar_Activity.this).setTitle("Berhasil")
//                            .setMessage(toShow)
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener(){
//                                @Override
//                                public void onClick(DialogInterface dialog, int id) {
//                                }
//                            }).show();
                }
        }
    }

    public void uploadToServer(String path){
        TypedFile typedFile = new TypedFile("multipart/form-data", new File(path));
        APIService service = ServiceGenerator.createService(APIService.class, userId, token);
        service.uploadFoto(typedFile, new Callback<ImageResponse>() {
            @Override
            public void success(ImageResponse imageResponse, Response response) {
                String id = "id: " + imageResponse.getId();
                String status = "status: " + imageResponse.getStatus();
                String message = "message: " + String.valueOf(imageResponse.getMessage());
                Toast.makeText(getApplicationContext(), id + "\n" + status + "\n" + message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
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
