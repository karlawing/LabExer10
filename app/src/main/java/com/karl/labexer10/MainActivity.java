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
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    EditText etMessage;
    EditText etFileName;
    FileInputStream fis;
    TextView tvMessage;
    TextView tvFileName;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMessage = (EditText) findViewById(R.id.editText_Data);
        tvMessage = (TextView) findViewById(R.id.textView);
        tvFileName = (TextView) findViewById(R.id.textView_Filename);
        etFileName = (EditText) findViewById(R.id.editText_filename);
        preferences = getSharedPreferences(etFileName.getText().toString(), Context.MODE_PRIVATE);
    }
    public void writeData(File file, String message){

        try{
            fos=new FileOutputStream(file);
            fos.write(message.getBytes());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveSharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("message",etMessage.getText().toString());
        editor.commit();
        Toast.makeText(this,"Successfully written to shared preference",Toast.LENGTH_LONG).show();
    }
    public void saveInternalStorage (View view) {
        String message = etMessage.getText().toString();
        try{
            fos = openFileOutput(etFileName.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to internal storage", Toast.LENGTH_LONG).show();
    }
    public void saveInternalCache(View view){
        File folder = getCacheDir();
        File file = new File(folder,etFileName.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to internal cache",Toast.LENGTH_LONG).show();

    }
    public void saveExternalCache(View view){
        File folder = getExternalCacheDir();
        File file = new File(folder,etFileName.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to external cache",Toast.LENGTH_LONG).show();
    }
    public void saveExternalStorage(View view){
        File folder = getExternalFilesDir("Temp");
        File file = new File(folder,etFileName.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to external storage",Toast.LENGTH_LONG).show();
    }
    public void saveExternalPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,etFileName.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to external public",Toast.LENGTH_LONG).show();

    }

    public void nextAct(View view){
        Intent myIntent = new Intent(this,
                Main2Activity.class);
        MainActivity.this.startActivity(myIntent);
    }

}