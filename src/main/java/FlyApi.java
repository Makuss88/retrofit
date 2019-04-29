import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.POST;
public interface FlyApi {

    @POST("/")
    Call<List<Fly>> loadFly (RequestBody requestBody);
}