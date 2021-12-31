package com.zahwaoctavioliena.ppb.uas.Apiclient;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface KasirInterface {
    @FormUrlEncoded
    @POST("api.php")
    Call<Kasir> postLogin(@Field("username")String username, @Field("password")String password);
}
