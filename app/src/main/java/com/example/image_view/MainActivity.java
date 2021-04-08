package com.example.image_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imageview;
    private Button b1;
    private BitmapDrawable bmpdraw;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = findViewById(R.id.Iv1);
        b1 = findViewById(R.id.s1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmpdraw = (BitmapDrawable) imageview.getDrawable();
                bmp = bmpdraw.getBitmap();
                File directory=new File(Environment.getExternalStorageDirectory()+ "/picture/");
                directory.mkdir();
                String fname=String.format("Quotes.jpg");
                File of=new File(directory,fname);
                Toast.makeText(MainActivity.this,"Image Saved Successfully to Internal Storage",Toast.LENGTH_LONG).show();
                try{
                    FileOutputStream outputStream = new FileOutputStream(of);
                    bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Intent intent= new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(of));
                    sendBroadcast(intent);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}