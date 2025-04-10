
package Api;// ClienteApi.java
import modelos.Cliente;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ClienteApi {
    @GET("clientes")
    Call<List<Cliente>> getClientes();

    @POST("clientes")
    Call<Cliente> crearCliente(@Body Cliente cliente);
}
