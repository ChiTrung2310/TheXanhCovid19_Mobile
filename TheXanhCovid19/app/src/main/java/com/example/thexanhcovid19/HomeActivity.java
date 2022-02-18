package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thexanhcovid19.api.ApiServicePost;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity{

    String temp,temp1,temp2,temp3,temp4,temp5, temp6, temp7;
    ImageButton QR;
    ImageView mana_QR;
    TableLayout tbL;
    TextView tv_TL;

    String[] permissions = {
            Manifest.permission.CAMERA
    };

    int PERM_CODE = 11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView PA = findViewById(R.id.pa);
        ImageView KB = findViewById(R.id.kp);
        ImageButton btnMore = findViewById(R.id.btnMore);
        ImageButton btnTB = findViewById(R.id.btnTB);
        ImageView qr = findViewById(R.id.avt);

        tbL = findViewById(R.id.thecovid);
        tv_TL = findViewById(R.id.tv_title);

        TextView tvInfo = findViewById(R.id.user);
        TextView tvCM = findViewById(R.id.ttCM);
        checkPermissions();


        mana_QR = findViewById(R.id.qr);
        mana_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Information_QR_Activity.class);
                startActivity(intent);
            }
        });

        QR = findViewById(R.id.btnQR);
        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScanQR_Activity.class);
                intent.putExtra("name", tvInfo.getText());
                intent.putExtra("cMND", temp);
                intent.putExtra("DT", temp1);
                intent.putExtra("mail", temp2);
                intent.putExtra("nS", temp3);
                intent.putExtra("gT", temp4);
                intent.putExtra("dC", temp5);
                intent.putExtra("qQ", temp6);
                startActivity(intent);

            }
        });

        Bundle bundleReceive = getIntent().getExtras();
        if (bundleReceive != null) {
            User user = (User) bundleReceive.get("object_user");
            if (user != null) {
                // Hiển thị tên và số CMND của người đăng nhập và sử dụng app
                tvInfo.setText(user.getHo() + " " + user.getTenDem() + " " + user.getTen());
                tvCM.setText(user.getSoCMND());

                if(user.getLoaiThe().equals("Thẻ Xanh")){
                    tbL.setBackgroundColor(Color.parseColor("#30B55C"));
                }

                else if(user.getLoaiThe().equals("Thẻ Vàng")){
                    tbL.setBackgroundColor(Color.parseColor("#BFBF00"));
                }

                else if(user.getLoaiThe().equals("Thẻ Đỏ")){
                    tbL.setBackgroundColor(Color.parseColor("#FF0000"));
                }

                tv_TL.setText("ĐÃ TIÊM " + user.getSoMuiTiem() + " MŨI VẮC XIN");

                temp = user.getSoCMND();
                temp1 = user.getDienThoai();
                temp2 = user.getEmail();
                temp3 = user.getNgaySinh();
                temp4 = user.getGioiTinh();
                temp5 = user.getDiaChi();
                temp6 = user.getQueQuan();
                temp7 = tvInfo.getText().toString();


                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(user.toString(), BarcodeFormat.QR_CODE, 350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    qr.setImageBitmap(bitmap);
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        }


        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);

            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MoreActivity.class);
                startActivity(intent);

            }
        });

        PA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReflectActivity.class);
                intent.putExtra("ten", tvInfo.getText());
                intent.putExtra("cMND", temp);
                intent.putExtra("DT", temp1);
                intent.putExtra("mail", temp2);
                startActivity(intent);
            }
        });

        KB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                Button btn2 = bottomSheetDialog.findViewById(R.id.btn2);
                Button btn3 = bottomSheetDialog.findViewById(R.id.btn3);
                Button btn4 = bottomSheetDialog.findViewById(R.id.btn4);
                Button btn5 = bottomSheetDialog.findViewById(R.id.btn5);
                TableLayout tbn = bottomSheetDialog.findViewById(R.id.table);

                tbn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendKBYT();
                        bottomSheetDialog.hide();
                    }
                });


                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(HomeActivity.this, health_declaration_Activity.class);
                        intent.putExtra("name", tvInfo.getText());
                        intent.putExtra("cMND", temp);
                        intent.putExtra("DT", temp1);
                        intent.putExtra("mail", temp2);
                        intent.putExtra("nS", temp3);
                        intent.putExtra("gT", temp4);
                        startActivity(intent);
                        bottomSheetDialog.hide();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, health_declaration_Activity.class);
                        startActivity(intent);
                        bottomSheetDialog.hide();
                    }
                });

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeActivity.this, "Chức năng đang phát triển", Toast.LENGTH_SHORT).show();
                    }
                });

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.hide();
                    }
                });

                bottomSheetDialog.show();
            }
        });
    }

        private void sendKBYT() {
            String a = temp7;
            String b = temp;
            String c = temp4;
            String d = temp3;
            String e = temp1;
            String f = "No Number";
            String g = temp2;
            String h = temp6;
            String i = temp5;
            String j = "";
            String k = "Không có";
            String m = "Không";
            String n = "Không";
            String o = "Không";


            KBYT kbyt = new KBYT(a, b, c, d, e, f, g, h, i, j, k, m, n, o);
            ApiServicePost.apiServicePost.sendPost(kbyt).enqueue(new Callback<KBYT>() {
                @Override
                public void onResponse(Call<KBYT> call, Response<KBYT> response) {
                    Toast.makeText(HomeActivity.this, "Khai báo y tế nhanh thành công", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<KBYT> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Khai báo y tế nhanh thất bại", Toast.LENGTH_SHORT).show();
                }
            });

    }
    // Hỏi để sử dụng quyền cho camera
    private boolean checkPermissions(){
        List<String> listOfPermissions = new ArrayList<>();
        for(String perm : permissions){
            if(ContextCompat.checkSelfPermission(getApplicationContext(), perm) != PackageManager.PERMISSION_GRANTED){
                listOfPermissions.add(perm);
            }
        }
        if(!listOfPermissions.isEmpty()){
            ActivityCompat.requestPermissions(this,listOfPermissions.toArray(new String[listOfPermissions.size()]),PERM_CODE);
            return false;
        }
        return true;
    }

}