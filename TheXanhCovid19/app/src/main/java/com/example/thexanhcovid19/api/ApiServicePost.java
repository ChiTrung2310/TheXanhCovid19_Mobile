package com.example.thexanhcovid19.api;

import com.example.thexanhcovid19.KBYT;
import com.example.thexanhcovid19.PhanAnh;
import com.example.thexanhcovid19.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiServicePost
{
    // Link API: http://thexanhcovid19.somee.com/api/WSTheXanh

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    ApiServicePost apiServicePost = new Retrofit.Builder()
            .baseUrl("http://thexanhcovid19.somee.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServicePost.class);


    //Ta cần thêm cái đuôi này ở phía sau nè bạn ơi
    @POST("KhaiBaoYTE?AspxAutoDetectCookieSupport=1")
    Call<KBYT> sendPost(@Body KBYT kbyt);
}
