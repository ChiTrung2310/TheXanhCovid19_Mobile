package com.example.thexanhcovid19.api;

import com.example.thexanhcovid19.PhanAnh;
import com.example.thexanhcovid19.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // Link API: http://thexanhcovid19.somee.com/api/WSTheXanh

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://thexanhcovid19.somee.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("WSTheXanh")
    Call<List<User>> getListUsers(); // có thể lỗi chỗ này

    @POST("WSTheXanh?AspxAutoDetectCookieSupport=1")
    Call<PhanAnh> sendPost(@Body PhanAnh phanAnh);
}
