package com.example.belajarretrofit.API;

import com.example.belajarretrofit.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("view_data.php")
    Call<ResponseModel> ardViewData();

    @FormUrlEncoded
    @POST("create_data.php")
    Call<ResponseModel> ardCreateData(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("prodi") String prodi,
            @Field("fakultas") String fakultas
    );

    @FormUrlEncoded
    @POST("delete_data.php")
    Call<ResponseModel> ardDeleteData (
            @Field("nim") String nim
    );

    @FormUrlEncoded
    @POST("get_data.php")
    Call<ResponseModel> ardgetData (
            @Field("nim") String nim
    );

    @FormUrlEncoded
    @POST("update_data.php")
    Call<ResponseModel> ardUpdateData(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("prodi") String prodi,
            @Field("fakultas") String fakultas
    );
}
