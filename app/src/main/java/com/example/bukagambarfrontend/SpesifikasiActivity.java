package com.example.bukagambarfrontend;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bukagambarfrontend.DialogFragments.DialogCheckbox;
import com.example.bukagambarfrontend.POJO.CategorPOJO.Attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpesifikasiActivity extends AppCompatActivity {

    List<Attribute> attrs;
    public static Map<String, String> mapOfAtrributes = new HashMap<>();
    public static String title;
    public static String[] options;
    int j = 0;
    Button simpanSpesifikasi;
    ImageButton closeSpesifikasi;
    public static List<String> choices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesifikasi);
        //adding Toolbar to Step satu
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_spesifikasiActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");

        simpanSpesifikasi = (Button) findViewById(R.id.simpan_spekbarang_button);
        closeSpesifikasi = (ImageButton) findViewById(R.id.close_spekbarang_button);

        closeSpesifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        simpanSpesifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StepSatuActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.inside_spesifikasi);

        attrs = StepSatuActivity.attributes;
        for(final Attribute a : attrs){
            if(a.getInputType().equals("select") || a.getInputType().equals("combo_select")){
                final Attribute at = a;
                TextView text = new TextView(this);
                Spinner spinner = new Spinner(this);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setText(a.getDisplayName());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, a.getOptions());
                spinner.setAdapter(adapter);
                layout.addView(text);
                layout.addView(spinner);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mapOfAtrributes.put(at.getFieldName(), at.getOptions().get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }else if(a.getInputType().equals("string")){
                TextView text = new TextView(this);
                EditText editText = new EditText(this);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setText(a.getDisplayName());
                layout.addView(text);
                layout.addView(editText);
                mapOfAtrributes.put(a.getFieldName(), editText.getText().toString());
            }else if(a.getInputType().equals("text")){
                TextView text = new TextView(this);
                EditText editText = new EditText(this);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setText(a.getDisplayName());
                editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editText.getBackground().setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                layout.addView(text);
                layout.addView(editText);
                mapOfAtrributes.put(a.getFieldName(), editText.getText().toString());
            }else if(a.getInputType().equals("radio_button")){
                TextView text = new TextView(this);
                RadioGroup radioGroup = new RadioGroup(this);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                radioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                text.setText(a.getDisplayName());
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                for(String option : a.getOptions()){
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(option);
                    radioGroup.addView(radioButton);
                }
                layout.addView(text);
                layout.addView(radioGroup);
                int radioId = radioGroup.getCheckedRadioButtonId();
                RadioButton checked = (RadioButton) findViewById(radioId);
                mapOfAtrributes.put(a.getFieldName(), checked.getText().toString());
            }else if(a.getInputType().equals("boolean") || a.getInputType().equals("check_boxes")){
                title = a.getDisplayName();
                String field = a.getFieldName();
                options = a.getOptions().toArray(new String[0]);
                ListView listView = new ListView(this);
                listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                List<String> content = new ArrayList<>();
                content.add(a.getDisplayName());
                ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, content);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FragmentManager fm = getSupportFragmentManager();
                        DialogCheckbox dc = new DialogCheckbox();
                        dc.show(fm, "Checkbox");
                    }
                });
                layout.addView(listView);
                if(choices != null){
                    mapOfAtrributes.put(field, TextUtils.join(", ", choices));
                }
            }
        }
    }
}
