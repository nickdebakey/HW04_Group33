package com.example.hw04_group33;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    String name;
    String description;
    int genre;
    int rating;
    String year;
    String IMDB;

    static String[] genres = {"Action", "Animation", "Comedy", "Documentary", "Family", "Horror", "Crime", "Others"};

    public Movie(String name, String description, int genre, int rating, String year, String IMDB) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.IMDB = IMDB;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        description = in.readString();
        genre = in.readInt();
        rating = in.readInt();
        year = in.readString();
        IMDB = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReview() {
        return IMDB;
    }

    public void setReview(String review) {
        this.IMDB = IMDB;
    }

    public static String[] getGenres() {
        return genres;
    }

    public static void setGenres(String[] genres) {
        Movie.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", rating=" + rating +
                ", year=" + year +
                ", review='" + IMDB + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(genre);
        parcel.writeInt(rating);
        parcel.writeString(String.valueOf(year));
        parcel.writeString(IMDB);
    }
}
