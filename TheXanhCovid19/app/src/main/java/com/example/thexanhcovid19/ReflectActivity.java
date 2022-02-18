package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thexanhcovid19.api.ApiService;
import com.example.thexanhcovid19.api.ApiServicePost;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReflectActivity extends AppCompatActivity {

    private MaterialButton guiPA;
    private TextInputEditText hT, cMND, phone, email, tD, nD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

        hT = findViewById(R.id.ten);
        cMND = findViewById(R.id.scMND);
        phone = findViewById(R.id.dT);
        email = findViewById(R.id.mails);
        tD = findViewById(R.id.tD);
        tD.requestFocus();
        nD = findViewById(R.id.nD);

        String name = getIntent().getStringExtra("ten");
        hT.setText(name);

        String soCM = getIntent().getStringExtra("cMND");
        cMND.setText(soCM);

        String dT = getIntent().getStringExtra("DT");
        phone.setText(dT);

        String eM = getIntent().getStringExtra("mail");
        email.setText(eM);



        guiPA = findViewById(R.id.buttonStart);
        guiPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
                finish();
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendPost(){
        String a = hT.getText().toString();
        String b = cMND.getText().toString();
        String c = phone.getText().toString();
        String d = email.getText().toString();
        String e = tD.getText().toString();
        String f = nD.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();

        PhanAnh phanAnh = new PhanAnh(a,b,c,d,e,f,currentTime);
        ApiService.apiService.sendPost(phanAnh).enqueue(new Callback<PhanAnh>() {
            @Override
            public void onResponse(Call<PhanAnh> call, Response<PhanAnh> response) {
                Toast.makeText(ReflectActivity.this, "Phản ánh thông tin thành công", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PhanAnh> call, Throwable t) {
                Toast.makeText(ReflectActivity.this, "Phản ánh thông tin thất bại", Toast.LENGTH_SHORT).show();

            }
        });

    }
}