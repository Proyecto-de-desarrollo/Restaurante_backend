package Api;// MesaApi.java
import modelos.Mesa;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MesaApi {
    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @POST("mesas")
    Call<Mesa> crearMesa(@Body Mesa mesa);
}
