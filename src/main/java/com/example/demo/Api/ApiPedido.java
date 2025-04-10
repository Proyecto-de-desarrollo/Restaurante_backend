package com.example.demo.Api;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

import com.example.demo.modelos.Pedido;

public interface ApiPedido {
    @GET("pedidos")
    Call<List<Pedido>> getPedidos();

    @POST("pedidos")
    Call<Pedido> crearPedido(@Body Pedido pedido);
}
