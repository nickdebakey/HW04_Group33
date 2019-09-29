package com.example.hw04_group33;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button bAddMovie, bEdit, bDeleteMovie, bShowListByYear, bShowListByRating;

    ArrayList<Movie> movies = new ArrayList<>();

    static final int ADD_MOVIE = 0;
    static final int EDIT_MOVIE = 1;

    static final String MOVIE_DATA = "movie";
    static final String MOVIES_DATA = "movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");

        bAddMovie = findViewById(R.id.bEditMovie);
        bEdit = findViewById(R.id.bEdit);
        bDeleteMovie = findViewById(R.id.bDeleteMovie);
        bShowListByYear = findViewById(R.id.bShowListByYear);
        bShowListByRating = findViewById(R.id.bShowListByRating);

        bAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(add, ADD_MOVIE);
            }
        });

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movies.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies", Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] names = new String[movies.size()];

                    for (int i = 0; i < movies.size(); i++) {
                        names[i] = movies.get(i).name;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie").setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent edit = new Intent(MainActivity.this, EditMovieActivity.class);
                            edit.putExtra(MOVIE_DATA, movies.get(i));
                            startActivityForResult(edit, EDIT_MOVIE);

                            movies.remove(movies.get(i));
                        }
                    });

                    AlertDialog delete = builder.create();
                    delete.show();
                }
            }
        });

        bDeleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movies.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies", Toast.LENGTH_SHORT).show();
                }
                else {
                    String[] names = new String[movies.size()];

                    for (int i = 0; i < movies.size(); i++) {
                        names[i] = movies.get(i).name;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie").setItems(names, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String deletedName = movies.get(i).name;
                            movies.remove(movies.get(i));
                            Toast.makeText(MainActivity.this, deletedName + " deleted", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog delete = builder.create();
                    delete.show();
                }
            }
        });

        bShowListByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movies.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent showYear = new Intent("com.example.hw04_group33.intent.action.SHOW_YEAR");
                    showYear.addCategory(Intent.CATEGORY_DEFAULT);
                    showYear.putParcelableArrayListExtra(MOVIES_DATA, movies);
                    startActivity(showYear);
                }
            }
        });

        bShowListByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movies.size() == 0) {
                    Toast.makeText(MainActivity.this, "No movies", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent showRating = new Intent("com.example.hw04_group33.intent.action.SHOW_RATING");
                    showRating.addCategory(Intent.CATEGORY_DEFAULT);
                    showRating.putParcelableArrayListExtra(MOVIES_DATA, movies);
                    startActivity(showRating);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Movie movie;

        if (requestCode == ADD_MOVIE) {
            if (resultCode == RESULT_OK) {
                movie = data.getExtras().getParcelable(MOVIE_DATA);
                movies.add(movie);
            }
        }
        else if (requestCode == EDIT_MOVIE) {
            if (resultCode == RESULT_OK) {
                movie = data.getExtras().getParcelable(MOVIE_DATA);
                movies.add(movie);
            }
        }
    }
}
