
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface FlyApi {

    @POST("/")
    Call<RequestBody> postSearch(@Body RequestBody requestBody);
}