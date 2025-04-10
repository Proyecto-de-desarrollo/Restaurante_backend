package com.example.demo.Api;// MesaApi.java
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

import com.example.demo.modelos.Mesa;

public interface MesaApi {
    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @POST("mesas")
    Call<Mesa> crearMesa(@Body Mesa mesa);
}
