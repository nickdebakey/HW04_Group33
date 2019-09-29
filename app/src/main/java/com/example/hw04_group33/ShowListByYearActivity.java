package com.example.hw04_group33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Collections.sort;

public class ShowListByYearActivity extends AppCompatActivity {
    TextView tvName, tvDescription, tvGenre, tvRating, tvYear, tvIMDB;
    ImageView ivFirst, ivPrevious, ivNext, ivLast;
    Button bFinish;

    ArrayList<Movie> movies;
    int currentMovie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_by_year);
        setTitle("Movies by Year");

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvGenre = findViewById(R.id.tvGenre);
        tvRating = findViewById(R.id.tvRating);
        tvYear = findViewById(R.id.tvYear);
        tvIMDB = findViewById(R.id.tvIMDB);
        ivFirst = findViewById(R.id.ivFirst);
        ivPrevious = findViewById(R.id.ivPrevious);
        ivNext = findViewById(R.id.ivNext);
        ivLast = findViewById(R.id.ivLast);
        bFinish = findViewById(R.id.bFinish);

        if (getIntent().hasExtra(MainActivity.MOVIES_DATA))
            movies = getIntent().getExtras().getParcelableArrayList(MainActivity.MOVIES_DATA);

        Collections.sort(movies, new Comparator<Movie>() {
            @Override public int compare(Movie m1, Movie m2) {
                return Integer.parseInt(m1.getYear()) - Integer.parseInt(m2.getYear()); // Ascending
            }
        });
        currentMovie = 0;
        updateInfo();

        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMovie = 0;
                updateInfo();
            }
        });

        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentMovie == 0) {
                    Toast.makeText(ShowListByYearActivity.this, "No previous movie", Toast.LENGTH_SHORT).show();
                } else {
                    currentMovie--;
                    updateInfo();
                }
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentMovie == movies.size() - 1) {
                    Toast.makeText(ShowListByYearActivity.this, "No next movie", Toast.LENGTH_SHORT).show();
                } else {
                    currentMovie++;
                    updateInfo();
                }
            }
        });

        ivLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMovie = movies.size() - 1;
                updateInfo();
            }
        });
    }

    public void updateInfo() {
        tvName.setText("Name: " + movies.get(currentMovie).name);
        tvDescription.setText("Description: " + movies.get(currentMovie).description);
        tvGenre.setText("Genre: " + Movie.genres[movies.get(currentMovie).genre]);
        tvRating.setText("Rating: " + movies.get(currentMovie).rating + " / 5");
        tvYear.setText("Year: " + movies.get(currentMovie).year);
        tvIMDB.setText("IMDB: " + movies.get(currentMovie).IMDB);
    }
}
