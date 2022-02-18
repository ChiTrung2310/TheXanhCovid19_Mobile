package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.nfc.tech.NfcB;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thexanhcovid19.api.ApiServicePost;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class health_declaration_Activity extends AppCompatActivity {


    TextInputEditText hoTen, cmND, birthday, phone, bHYT, email, province, district, ward, location;
    RadioButton radioButton, radioButton1, radioButton2, radioButton3, male, female;
    RadioGroup gT, kV, nB, tC;
    MaterialButton kBYT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_declaration);

        birthday = findViewById(R.id.ddmmyy);
        hoTen = findViewById(R.id.ten);
        cmND = findViewById(R.id.cMND);
        phone = findViewById(R.id.telephone);
        bHYT = findViewById(R.id.bHYT);
        email = findViewById(R.id.mail);
        province = findViewById(R.id.provinces);
        district = findViewById(R.id.districts);
        ward = findViewById(R.id.ward);
        location = findViewById(R.id.locations);
        location.setText("Không có");

        gT = findViewById(R.id.GT);
        kV = findViewById(R.id.KVNN);
        nB = findViewById(R.id.TXNB);
        tC = findViewById(R.id.TC);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        // Gán sẵn thông tin cho việc khai báo y tế
        String name = getIntent().getStringExtra("name");
        hoTen.setText(name);

        String soCM = getIntent().getStringExtra("cMND");
        cmND.setText(soCM);

        String dT = getIntent().getStringExtra("DT");
        phone.setText(dT);

        String eM = getIntent().getStringExtra("mail");
        email.setText(eM);

        String nS = getIntent().getStringExtra("nS");
        birthday.setText(nS);

        //Kiểm tra giới tính người đăng nhập
        String gT = getIntent().getStringExtra("gT");
        if(gT != null){
            if(gT.equals("Nam")){
                male.setChecked(true);
            }else {
                female.setChecked(true);
            }
        }


        kBYT = (MaterialButton) findViewById(R.id.buttonStart);
        kBYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hoTen.getText().length() > 0 ){
                    if(cmND.getText().length() > 0){
                        if(male.isChecked() || female.isChecked()){
                            sendKBYT();
                            finish();
                        }else {
                            Toast.makeText(health_declaration_Activity.this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();

                            male.setFocusable(true);
                            male.setFocusableInTouchMode(true);
                            male.requestFocus();
                        }

                    }else {
                        Toast.makeText(health_declaration_Activity.this, "Vui lòng điền số CMND", Toast.LENGTH_SHORT).show();
                        cmND.requestFocus();
                    }

                }else {
                    Toast.makeText(health_declaration_Activity.this, "Vui lòng điền họ tên", Toast.LENGTH_SHORT).show();
                    hoTen.requestFocus();
                }

            }
        });

        ImageButton btnBack = findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });

    }

    private void chonNgay(){
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                birthday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }


    private void sendKBYT(){
        int selectedId = gT.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

        int selectedId2 = kV.getCheckedRadioButtonId();
        radioButton1 = findViewById(selectedId2);

        int selectedId3 = nB.getCheckedRadioButtonId();
        radioButton2 = findViewById(selectedId3);

        int selectedId4 = tC.getCheckedRadioButtonId();
        radioButton3 = findViewById(selectedId4);


            String a = hoTen.getText().toString();
            String b = cmND.getText().toString();
            String c = radioButton.getText().toString();
            String d = birthday.getText().toString();
            String e = phone.getText().toString();
            String f = bHYT.getText().toString();
            String g = email.getText().toString();
            String h = province.getText().toString();
            String i = district.getText().toString();
            String j = ward.getText().toString();
            String k = location.getText().toString();
            String m = radioButton1.getText().toString();
            String n = radioButton2.getText().toString();
            String o = radioButton3.getText().toString();


            KBYT kbyt = new KBYT(a, b, c, d, e, f, g, h, i, j, k, m, n, o);
            ApiServicePost.apiServicePost.sendPost(kbyt).enqueue(new Callback<KBYT>() {
                @Override
                public void onResponse(Call<KBYT> call, Response<KBYT> response) {
                    Toast.makeText(health_declaration_Activity.this, "Khai báo y tế thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<KBYT> call, Throwable t) {
                    Toast.makeText(health_declaration_Activity.this, "Khai báo y tế thất bại", Toast.LENGTH_SHORT).show();
                }
            });

    }
}