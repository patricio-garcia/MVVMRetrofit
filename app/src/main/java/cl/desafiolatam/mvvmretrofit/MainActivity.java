package cl.desafiolatam.mvvmretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.desafiolatam.mvvmretrofit.adapter.MovieListAdapter;
import cl.desafiolatam.mvvmretrofit.model.MovieModel;
import cl.desafiolatam.mvvmretrofit.viewmodel.MovieListViewModel;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private  MovieListAdapter adapter;
    private MovieListViewModel viewModel;
    private TextView tvNoResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvNoResult = findViewById(R.id.tvNoResults);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(this, movieModelList, this::onMovieClick);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    tvNoResult.setVisibility(View.GONE);
                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.makeApiCall();
    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Toast.makeText(this, "El nombre de la película es: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }

}