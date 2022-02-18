package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class QR_Activity extends AppCompatActivity {

    EditText location, TP, QH, PX, SN;
    Button btnCreate;
    ImageView qr_Img;
    ImageButton btn_Back;

    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/Pictures/";

    public static String bB = "";
    public String text1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";


    private Button btnAdd;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<TaskModel> taskList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        location = findViewById(R.id.tv_location);
        TP = findViewById(R.id.ed_state);
        QH = findViewById(R.id.ed_district);
        PX = findViewById(R.id.ed_ward);
        SN = findViewById(R.id.ed_soNha);
        location.requestFocus();

        // đọc task list được lưu trữ từ trước trong máy
        taskList = PrefConfig.readListFromPref(this);

        btn_Back = findViewById(R.id.buttonBack);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnCreate = findViewById(R.id.btn_create_QR);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(QR_Activity.this);
                bottomSheetDialog.setContentView(R.layout.qr_layout);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                qr_Img = bottomSheetDialog.findViewById(R.id.A194);

                String A = location.getText().toString();
                String B = TP.getText().toString();
                String C = QH.getText().toString();
                String D = PX.getText().toString();
                String E = SN.getText().toString();

                String data = E +"-"+ A +"-"+ D +"-"+ C +"-"+ B;
                if(data.isEmpty()){
                    Toast.makeText(QR_Activity.this,"Chưa có dữ liệu!", Toast.LENGTH_SHORT).show();
                    location.requestFocus();
                }else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    Bitmap qrBit = qrgEncoder.getBitmap();
                    qr_Img.setImageBitmap(qrBit);

                    //Phần code này dùng để lưu mã QR về máy
                    QRGSaver qrgSaver = new QRGSaver();
                    qrgSaver.save(savePath, location.getText().toString().trim(), qrBit, QRGContents.ImageType.IMAGE_JPEG );


                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        try {
                            boolean save = new QRGSaver().save(savePath, location.getText().toString().trim(), qrBit, QRGContents.ImageType.IMAGE_JPEG);
                            String result = save ? "Đã lưu mã QR" : "Lỗi lưu trữ";
                            Toast.makeText(QR_Activity.this, result, Toast.LENGTH_LONG).show();
                            bB = location.getText().toString(); // có thể lỗi chỗ này

                            saveData(); // có thể lỗi chổ này

//
//                            if (taskList == null)
//                                taskList = new ArrayList<>();
//
//                            TaskModel taskModel = new TaskModel(location.getText().toString(), getDate());
//                            taskList.add(taskModel);
//                            PrefConfig.writeListInPref(getApplicationContext(), taskList);
//                            Collections.reverse(taskList);
//                            adapter.setTaskModelList(taskList);
//
//                            if (!location.getText().equals(""))
//                                location.setText("");


                            location.setText(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        ActivityCompat.requestPermissions(QR_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    }

                    bottomSheetDialog.show();
                }

                Button btn1 = bottomSheetDialog.findViewById(R.id.btn_create_QR);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bottomSheetDialog.hide();
                        Intent intent = new Intent(QR_Activity.this, Information_QR_Activity.class);
                        startActivity(intent);

                        finish();
                    }
                });

            }
        });
    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, location.getText().toString());
        editor.apply();
        Toast.makeText(QR_Activity.this,"Đã Lưu Mã QR địa điểm của bạn", Toast.LENGTH_SHORT).show();
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }


}