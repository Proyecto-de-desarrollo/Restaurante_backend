package Api;

import modelos.Pedido;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PedidoApi {

    @GET("api/pedidos")
    Call<List<Pedido>> getPedidos();

    @GET("api/pedidos/{id}")
    Call<Pedido> getPedidoById(@Path("id") String id);

    @POST("api/pedidos")
    Call<Pedido> createPedido(@Body Pedido pedido);

    @PUT("api/pedidos/{id}")
    Call<Pedido> updatePedido(@Path("id") String id, @Body Pedido pedido);

    @DELETE("api/pedidos/{id}")
    Call<Void> deletePedido(@Path("id") String id);

    @GET("api/pedidos/cliente/{clienteId}")
    Call<List<Pedido>> getPedidosByCliente(@Path("clienteId") String clienteId);

    @GET("api/pedidos/mesa/{mesaId}")
    Call<List<Pedido>> getPedidosByMesa(@Path("mesaId") String mesaId);
}
