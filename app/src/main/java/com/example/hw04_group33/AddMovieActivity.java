package com.example.hw04_group33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AddMovieActivity extends AppCompatActivity {
    TextView tvName, tvDescription, tvGenre, tvRating, tvRatingValue, tvYear, tvIMDB;
    EditText etName, etDescription, etYear, etIMDB;
    Spinner sGenre;
    SeekBar sbRating;
    Button bAddMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvGenre = findViewById(R.id.tvGenre);
        tvRating = findViewById(R.id.tvRating);
        tvRatingValue = findViewById(R.id.tvRatingValue);
        tvYear = findViewById(R.id.tvYear);
        tvIMDB = findViewById(R.id.tvIMDB);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etYear = findViewById(R.id.etYear);
        etIMDB = findViewById(R.id.etIMDB);
        sGenre = findViewById(R.id.sGenre);
        sbRating = findViewById(R.id.sbRating);
        bAddMovie = findViewById(R.id.bEditMovie);

        sbRating.setMax(5);
        tvRatingValue.setText(sbRating.getProgress() + "");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Movie.genres);
        sGenre.setAdapter(adapter);
        sGenre.setSelection(7);

        bAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputValid()) {
                    String name = etName.getText().toString();
                    String description = etDescription.getText().toString();
                    int genre = sGenre.getSelectedItemPosition();
                    int progress = sbRating.getProgress();
                    String year = etYear.getText().toString();
                    String IMDB = etIMDB.getText().toString();

                    Movie movie = new Movie(name, description, genre, progress, year, IMDB);

                    Intent result = new Intent();
                    result.putExtra(MainActivity.MOVIE_DATA, movie);

                    setResult(RESULT_OK, result);
                    finish();
                }
            }
        });

        sbRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvRatingValue.setText(sbRating.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public boolean inputValid() {
        boolean valid = true;

        if (etName.getText().toString() == null || etName.getText().toString().length() == 0) {
            etName.setError("Field empty");
            valid = false;
        }
        else if (etName.getText().toString().length() > 50) {
            etName.setError("Too long");
            valid = false;
        }
        if (etDescription.getText().toString() == null || etDescription.getText().toString().length() == 0) {
            etDescription.setError("Field empty");
            valid = false;
        }
        else if (etDescription.getText().toString().length() > 1000) {
            etDescription.setError("Too long");
            valid = false;
        }
        if (etYear.getText().toString() == null || etDescription.getText().toString().length() == 0) {
            etYear.setError("Field empty");
            valid = false;

        }
        if (etIMDB.getText().toString() == null || etIMDB.getText().toString().length() == 0) {
            etIMDB.setError("Field empty");
            valid = false;
        }

        return valid;
    }
}
