
package com.example.demo.Api;// ClienteApi.java
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

import com.example.demo.modelos.Cliente;

public interface ClienteApi {
    @GET("clientes")
    Call<List<Cliente>> getClientes();

    @POST("clientes")
    Call<Cliente> crearCliente(@Body Cliente cliente);
}
