package com.sovietcity.Model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface SovietApiService {

    @FormUrlEncoded
    @POST("/sovet/update_record.php")
    Call<Response> setRecord(
            @Field("mail") String email,
            @Field("money") double money,
            @Field("happiness") int happiness);

    @GET("/sovet/get_records.php")
    Call<Record[]> getRecords();

    @FormUrlEncoded
    @POST("/sovet/new_user.php")
    Call<Response> addUser(
            @Field("name") String name,
            @Field("mail") String mail);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://balt-music.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final SovietApiService instance = retrofit.create(SovietApiService.class);
}
