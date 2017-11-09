package com.karl.labexer10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;

public class Main2Activity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView tvMessage;
    EditText etFileName;
    FileInputStream fis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvMessage = (TextView) findViewById(R.id.textView_Data);
        etFileName = (EditText) findViewById(R.id.editText_filename);
        preferences = getSharedPreferences(etFileName.getText().toString(), Context.MODE_PRIVATE);
    }
    public void loadSharedPreference(View view){
        String message = preferences.getString("message","");
        tvMessage.setText(message);

    }
    public void displayMessage(View view){
        StringBuffer buffer = new StringBuffer();
        int read=0;
        try{
            fis = openFileInput(etFileName.getText().toString());
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        tvMessage.setText(buffer.toString());

    }
    public void loadInternalCache(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getCacheDir();
        File file = new File(folder,etFileName.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tvMessage.setText(stringBuffer.toString());
    }
    public void loadExternalCache(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getExternalCacheDir();
        File file = new File(folder,etFileName.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tvMessage.setText(stringBuffer.toString());
    }
    public void loadExternalStorage(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getExternalFilesDir("Temp");
        File file = new File(folder,etFileName.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tvMessage.setText(stringBuffer.toString());
    }
    public void loadExternalPublicStorage(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,etFileName.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tvMessage.setText(stringBuffer.toString());
    }
    public void goback(View view){
        Intent myIntent = new Intent(this,
                MainActivity.class);
        Main2Activity.this.startActivity(myIntent);
    }
}