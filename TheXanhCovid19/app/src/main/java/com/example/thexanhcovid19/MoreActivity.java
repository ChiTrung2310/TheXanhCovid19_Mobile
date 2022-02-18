package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        ImageButton btnTB = (ImageButton) findViewById(R.id.btnTB);

        ImageView btnHD = (ImageView) findViewById(R.id.HD);
        ImageView btnCD = (ImageView) findViewById(R.id.CD);
        ImageView btnGT = (ImageView) findViewById(R.id.GT);

        ImageView btnLC = (ImageView) findViewById(R.id.loco);
        btnLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, Location_Activity.class);
                startActivity(intent);
            }
        });
        ImageView btnDL = (ImageView) findViewById(R.id.DL);
        btnDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoreActivity.this,"Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView btnHL = (ImageView) findViewById(R.id.HL);
        btnHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoreActivity.this,"Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });

        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, InstructActivity.class);
                startActivity(intent);

            }
        });

        btnGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, IntroduceActivity2.class);
                startActivity(intent);

            }
        });

        btnCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MoreActivity.this,"Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });

        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, NotificationActivity.class);
                startActivity(intent);

            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}