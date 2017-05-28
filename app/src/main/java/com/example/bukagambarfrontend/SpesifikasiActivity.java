package com.example.bukagambarfrontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.bukagambarfrontend.POJO.CategorPOJO.Attribute;

import java.util.List;

public class SpesifikasiActivity extends AppCompatActivity {

    List<Attribute> attrs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesifikasi);
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_spesifikasi);

        attrs = StepSatuActivity.attributes;
        for(int i = 0; i < attrs.size(); i++){
            if(attrs.get(i).getInputType().equals("select")){
                Spinner spinner = new Spinner(this);
                spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(spinner, i);
            }
        }
    }
}
