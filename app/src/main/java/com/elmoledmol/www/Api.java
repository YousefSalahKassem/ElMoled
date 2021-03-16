package com.elmoledmol.www;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("api/Account/Register")
    Call<ResponseBody> createuser(
            @Field("Email") String email,
            @Field("Address") String address,
            @Field("Address2") String address2,
            @Field("Address3") String Address3,
            @Field("PhoneNumber") String phone,
            @Field("Name") String name,
            @Field("Password") String Password,
            @Field("ConfirmPassword") String ConfirmPassword
    );

    @FormUrlEncoded
    @POST("Token")
    Call<ResponseBody> login(
            @Field("password") String password,
            @Field("grant_type") String grant,
            @Field("username") String username

    );



    @POST("api/Order/AddAdOrder")
    Call<ResponseBody> method(
            @Header("Authorization") String authorization,
            @Body MyData object
            );

}
