package com.camillepradel.movierecommender.model;

/**
 * Created by a.chentouf on 30/11/2016.
 */

import com.camillepradel.movierecommender.DBConnections.Neo4j;

import java.util.Arrays;

public class Rating {

    private Movie movie;
    private int userId;
    private int score;
    private Neo4j ConnexionNeo4j;

    public Rating() {
        this.movie = null;
        this.userId = 0;
        this.score = 0;
    }

    public Rating(Movie movie, int userId, int score) {
        this.movie = movie;
        this.userId = userId;
        this.score = score;
    }

    public Rating(Movie movie, int userId) {
        this.movie = movie;
        this.userId = userId;
        this.score = 0;
    }

	public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
		this.movie = movie;
	}

    // pseudo getter and setter for movie id
    // (in order to automatically serialize Rating object on form submission)

	public int getMovieId() {
        return (int) this.movie.getId();
    }

    public void setMovieId(int movieId) {

        // TODO: get movie with id movieId from database
        //On a gerer ca dans les fonctions Neo4j.saveOrUpdateRating Neo4j.createRating.
        String title = "Titre";
        Genre genre0 = new Genre(0, "genre0");
        Genre genre1 = new Genre(1, "genre1");
        this.movie = new Movie(movieId, title, Arrays.asList(new Genre[] {genre0, genre1}));
    }

    public int getUserId() {
        return this.userId;
    }

	public void setUserId(int userId) {
		this.userId = userId;
	}

    public int getScore() {
        return this.score;
    }

	public void setScore(int score) {
		this.score = score;
	}
}
