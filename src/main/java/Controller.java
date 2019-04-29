import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.*;

public class Controller {

    String BASE_URL = "https://be.wizzair.com/9.9.0/Api/search/search/";

    String json = "{\"isFlightChange\":false,\"isSeniorOrStudent\":false,\"flightList\"" +
            ":[{\"departureStation\":\"KRK\",\"arrivalStation\":\"BRI\",\"departureDate\"" +
            ":\"2019-05-09\"}],\"adultCount\":1,\"childCount\":0,\"infantCount\":0,\"wdc\":true}";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlyApi flyApi = retrofit.create(FlyApi.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);

        flyApi.loadFly(requestBody).enqueue(new Callback<List<Fly>>() {

            @Override
            public void onResponse(Call<List<Fly>> call, Response<List<Fly>> response) {
                response.body().toString();
            }

            @Override
            public void onFailure(Call<List<Fly>> call, Throwable t) {

            }
        });

    }

}