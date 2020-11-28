package cl.desafiolatam.mvvmretrofit.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL = "https://velmm.com/apis/"; //volley_array.json
    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient () {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
