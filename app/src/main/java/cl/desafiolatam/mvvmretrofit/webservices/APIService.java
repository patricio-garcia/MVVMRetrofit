package cl.desafiolatam.mvvmretrofit.webservices;



import java.util.List;

import cl.desafiolatam.mvvmretrofit.model.MovieModel;
import retrofit2.http.GET;
import retrofit2.Call;

public interface APIService {
    @GET("volley_array.json")
    Call<List<MovieModel>> getMovieList();
}
