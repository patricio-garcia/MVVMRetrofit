package cl.desafiolatam.mvvmretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cl.desafiolatam.mvvmretrofit.R;
import cl.desafiolatam.mvvmretrofit.model.MovieModel;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListAdapterVH> {

    Context context;
    private List<MovieModel> movieList;
    private ItemClickListener clickListener;

    public MovieListAdapter(Context context, List<MovieModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new MovieListAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapterVH holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMovieClick(movieList.get(position));
            }
        });

        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        if (this.movieList != null) {
            return this.movieList.size();
        }
        return 0;
    }


    public class MovieListAdapterVH extends RecyclerView.ViewHolder {

        ImageView imgMovie;
        TextView tvTitle;

        public MovieListAdapterVH (@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.imgMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

    }

    public interface ItemClickListener {
        public  void onMovieClick(MovieModel movieModel);
    }

}
