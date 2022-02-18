package com.example.thexanhcovid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.thexanhcovid19.api.ApiServicePost;
import com.google.zxing.Result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanQR_Activity extends AppCompatActivity {

    TextView txt;
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    Button btnBack, btn_kBYT, btn_xN;

    String hT, cM, sDT, mail, birthday, sex, address, home_town;


    private Button btnAdd;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    private List<TaskModel> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        taskList = PrefConfig.readListFromPref(this);


        String name = getIntent().getStringExtra("name");
        hT = name;

        String soCM = getIntent().getStringExtra("cMND");
        cM = soCM;

        String dT = getIntent().getStringExtra("DT");
        sDT = dT;

        String eM = getIntent().getStringExtra("mail");
        mail = eM;

        String nS = getIntent().getStringExtra("nS");
        birthday = nS;

        String gT = getIntent().getStringExtra("gT");
        sex = gT;

        String dC = getIntent().getStringExtra("dC");
        address = dC;

        String qQ = getIntent().getStringExtra("qQ");
        home_town = qQ;


        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_kBYT = findViewById(R.id.btn_kBYT);
        btn_kBYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanQR_Activity.this, health_declaration_Activity.class);
                startActivity(intent);
                finish();

            }
        });

        btn_xN = findViewById(R.id.btn_xN);
        btn_xN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendKBYT();

                // Dòng bên dưới có thể gây loạn trong sắp xếp. cần được nghiên cứu lại
//              adapter = new TaskAdapter(getApplicationContext(), taskList);

                // có thể lỗi từ chỗ này
                if (taskList == null)
                    taskList = new ArrayList<>();

                TaskModel taskModel = new TaskModel(txt.getText().toString(), getDate());
                taskList.add(taskModel);
                PrefConfig.writeListInPref(getApplicationContext(), taskList);
                Collections.reverse(taskList);
//                adapter.setTaskModelList(taskList);

                if (!txt.getText().equals(""))
                    txt.setText("");

                finish();
            }
        });

        txt = findViewById(R.id.tv_location);
        codeScannerView = (CodeScannerView) findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this,codeScannerView);


        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(result.getText());
                        txt.setBackgroundColor(0x00FF00);

                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCamera();
    }

    private void requestCamera() {
        codeScanner.startPreview();
    }

    private void sendKBYT(){

        String a = hT;
        String b = cM;
        String c = sex;
        String d = birthday;
        String e = sDT;
        String f = "No number";
        String g = mail;
        String h = home_town;
        String i = address;
        String j = "";
        String k = txt.getText().toString();
        String m = "Không";
        String n = "Không";
        String o = "Không";


        KBYT kbyt = new KBYT(a, b, c, d, e, f, g, h, i, j, k, m, n, o);
        ApiServicePost.apiServicePost.sendPost(kbyt).enqueue(new Callback<KBYT>() {
            @Override
            public void onResponse(Call<KBYT> call, Response<KBYT> response) {
                Toast.makeText(ScanQR_Activity.this, "Đã lưu thông tin", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<KBYT> call, Throwable t) {
                Toast.makeText(ScanQR_Activity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }

}