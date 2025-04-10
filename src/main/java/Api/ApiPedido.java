package Api;
import modelos.Pedido;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiPedido {
    @GET("pedidos")
    Call<List<Pedido>> getPedidos();

    @POST("pedidos")
    Call<Pedido> crearPedido(@Body Pedido pedido);
}
