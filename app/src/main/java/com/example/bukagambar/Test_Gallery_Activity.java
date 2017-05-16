package com.example.bukagambar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class Test_Gallery_Activity extends AppCompatActivity {

    ArrayList<String> filePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__gallery_);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case FilePickerConst.REQUEST_CODE:
                if(resultCode==RESULT_OK && data!=null)
                {
                    filePaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    //use them anywhere
                }
        }
    }

    public void uploadOnClick(View view) {
        FilePickerBuilder.getInstance().setMaxCount(5)
                .setSelectedFiles(filePaths)
                .setActivityTheme(R.style.AppTheme)
                .pickPhoto(this);
    }
}
