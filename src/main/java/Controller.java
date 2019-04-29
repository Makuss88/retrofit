import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import retrofit2.*;

public class Controller implements Callback<List<String>> {

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

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);

        Call<RequestBody> call = flyApi.postSearch(requestBody);

        System.out.println(call);

    }

    @Override
    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
        if (response.isSuccessful()) {
            List<String> changesList = response.body();
            changesList.forEach(change -> System.out.println(change));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<String>> call, Throwable t) {
        t.printStackTrace();
    }
}