package com.example.thexanhcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thexanhcovid19.api.ApiService;
import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<User> mListUser;
    private User mUser;
    private EditText username;
    private EditText password;
    private MaterialButton btnLogin;
    private TextView dK;

    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (MaterialButton) findViewById(R.id.buttonLogin);
        TextView textView = (TextView) findViewById(R.id.backhome);
        checkBox = findViewById(R.id.checkbox);

        dK = findViewById(R.id.sudung);
        dK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });

        mListUser = new ArrayList<>();
        getListUsers();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void clickLogin(){
        if(username.length() > 0) {
            if (password.length() > 0) {
                if (checkBox.isChecked()) {
                    String strUserName = username.getText().toString().trim();

                    String strPassword = md5(password.getText().toString().trim());

                    if (mListUser == null || mListUser.isEmpty()) {
                        return;
                    }

                    boolean isHasUser = false;

                    for (User user : mListUser) {
                        if (strUserName.equals(user.getUserName()) && strPassword.equals(user.getMatKhau())) {
                            isHasUser = true;
                            mUser = user;
                            break;
                        }
                    }

                    if (isHasUser) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_user", mUser);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, " Sai username hoặc password ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, " Vui lòng đồng ý với điều khoản sử dụng ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, " Chưa nhập Password", Toast.LENGTH_SHORT).show();
                password.requestFocus();
            }
        }else {
            Toast.makeText(MainActivity.this, " Chưa nhập Username ", Toast.LENGTH_SHORT).show();
            username.requestFocus();
        }

    }


    //Hàm mã hóa mật khẩu thành md5 dùng để đăng nhập
    public static String md5(String text) {
        MessageDigest digest = null;
        try {
        // Create MD5 Hash
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());

            StringBuffer sb = new StringBuffer();
            for (byte b : result){
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if(hex.length() == 1){
                    sb.append("0"+hex);
                }
                else {
                    sb.append(hex);
                }
            }
            Log.e("Chuỗi MD5",sb.toString());
        return sb.toString();

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
        return "";

    }


    private void getListUsers() {
        ApiService.apiService.getListUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUser = response.body();
                Log.e("List User", mListUser.size() + "");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this," Call API ERROR ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}