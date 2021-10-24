package com.example.managementfile;

import android.widget.TextView;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        showText = (TextView) findViewById(R.id.getText);
    }

    public void back(View view){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void getPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //Folder Name
        File myfile = new File (folder, "myData1.txt"); // Filename
        String text = getdata(myfile);
        if (text != null){
            showText.setText(text);
        }else{
            showText.setText("No Data");
        }
    }

    public void getPrivate (View view) {
        File folder = getExternalFilesDir("Cindy"); //Folder Name
        File myFile = new File(folder, "myData2.txt"); //File name
        String text = getdata(myFile);
        if (text != null){
            showText.setText(text);
        }else{
            showText.setText("No Data");
        }
    }

    public String getdata(File myFile){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(myFile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1){
                buffer.append((char) i);
            }
            return buffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}