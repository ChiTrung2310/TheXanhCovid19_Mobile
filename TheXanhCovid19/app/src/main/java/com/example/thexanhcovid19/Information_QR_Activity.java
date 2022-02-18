package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Information_QR_Activity extends AppCompatActivity {

    Button btnQRCode, btnBack;
    String a = QR_Activity.bB;

    public String text1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_qr);
        loadData();
        update();

        btnBack = findViewById(R.id.btn_Back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnQRCode = findViewById(R.id.btn_create_QR);
        btnQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Information_QR_Activity.this, QR_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        File imgFile = new  File("/storage/emulated/0/Pictures/"+a+".jpg");

        if(imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.A12);

            myImage.setImageBitmap(myBitmap);

        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT,"");

    }

    public void update(){
        a = text1;
        Toast.makeText(Information_QR_Activity.this,"QR CODE ĐỊA ĐIỂM CỦA BẠN", Toast.LENGTH_SHORT).show();
    }
}