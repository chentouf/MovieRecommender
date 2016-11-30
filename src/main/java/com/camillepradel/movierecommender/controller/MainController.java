package com.camillepradel.movierecommender.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.camillepradel.movierecommender.DBConnections.MongoDB;
import com.camillepradel.movierecommender.DBConnections.Neo4j;
import com.camillepradel.movierecommender.model.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.camillepradel.movierecommender.model.Genre;
import com.camillepradel.movierecommender.model.Movie;

@Controller
public class MainController {
	String message = "Welcome to Spring MVC!";
	private Neo4j neo4j;
	private MongoDB mongodb;
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping("/moviesNeo4j")
	public ModelAndView showMoviesNeo4j(

		@RequestParam(value = "user_id", required = false) Integer userId) {

		ArrayList<Movie> moviesNeo4j = null;
		neo4j = new Neo4j();
		if(userId == null){
			moviesNeo4j = neo4j.getAllMovies();
			System.out.println("show all Movies");
		}
		else {
			moviesNeo4j = neo4j.getAllMoviesByUserId(userId);
			System.out.println("show Movies of user " + userId);
		}
		// TODO: write query to retrieve all movies from DB or all movies rated by user with id userId,
		// depending on whether or not a value was given for userId


		ModelAndView mv = new ModelAndView("moviesNeo4j");
		mv.addObject("userId", userId);
		mv.addObject("moviesNeo4j", moviesNeo4j);
		mv.addObject("moviesNeo4JSize", moviesNeo4j.size());
		neo4j.close();
		return mv;
	}

	@RequestMapping("/moviesMongoDB")
	public ModelAndView showMoviesMongoDB(
			@RequestParam(value = "user_id", required = false) Integer userId) {

		ArrayList<Movie> moviesMongoDB= null;
		mongodb = new MongoDB();
		if(userId == null){
			moviesMongoDB = mongodb.getAllMovies();
			System.out.println("show all Movies");
		}
		else {
			moviesMongoDB = mongodb.getAllMoviesByUserId(userId);
			System.out.println("show Movies of user " + userId);
		}

		ModelAndView mv = new ModelAndView("moviesMongoDB");
		mv.addObject("userId", userId);
		mv.addObject("moviesMongoDB", moviesMongoDB);
		mv.addObject("moviesMongoDBSize", moviesMongoDB.size());
		return mv;
	}


	@RequestMapping(value = "/moviesRatingsNeo4j", method = RequestMethod.GET)
	public ModelAndView showMoviesRatingsNeo4j(
		@RequestParam(value = "user_id", required = true) Integer userId) {
		System.out.println("GET /movieratings for user " + userId);

		List<Rating> moviesRatingsNeo4j= new LinkedList<Rating>();
		List<Movie> allMovies = new LinkedList<Movie>();
		neo4j = new Neo4j();
		if(userId != null){
			allMovies = neo4j.getAllMovies();
			moviesRatingsNeo4j = neo4j.getAllMoviesWithRatings(userId);
			System.out.println("show the movies rated by : " + userId);
		}

		ModelAndView mv = new ModelAndView("moviesRatingsNeo4j");
		mv.addObject("userId", userId);
		mv.addObject("allMovies",allMovies);
		mv.addObject("moviesRatingsNeo4j", moviesRatingsNeo4j);
		mv.addObject("moviesRatingsNeo4JSize", moviesRatingsNeo4j.size());
		mv.addObject("allMoviesSize",allMovies.size());
		neo4j.close();
		return mv;
	}

	@RequestMapping(value = "/moviesRatingsMongoDB", method = RequestMethod.GET)
	public ModelAndView showMoviesRatingsMongoDB(
			@RequestParam(value = "user_id", required = true) Integer userId) {
		System.out.println("GET /movieratings for user " + userId);

		List<Movie> allMoviesMongoDB= new LinkedList<Movie>();
		List<Rating> moviesRatingsMongoDB= new LinkedList<Rating>();
		mongodb = new MongoDB();
		if(userId != null){
			allMoviesMongoDB = mongodb.getAllMovies();
			moviesRatingsMongoDB = mongodb.getRatedMoviesByUserId(userId);
			System.out.println("show the movies rated by : " + userId);
		}

		ModelAndView mv = new ModelAndView("moviesRatingsMongoDB");
		mv.addObject("userId", userId);
		mv.addObject("allMovies",allMoviesMongoDB);
		mv.addObject("moviesRatingsMongoDB", moviesRatingsMongoDB);
		mv.addObject("moviesRatingsMongoDBJSize", moviesRatingsMongoDB.size());
		mv.addObject("allMoviesSize",allMoviesMongoDB.size());
		return mv;
	}

	@RequestMapping(value = "/movieratings", method = RequestMethod.POST)
		public String saveOrUpdateRating(@ModelAttribute("rating") Rating rating) {
			System.out.println("POST /movieratings for user " + rating.getUserId()
												+ ", movie " + rating.getMovie().getId()
												+ ", score " + rating.getScore());

			// TODO: add query which
			//         - add rating between specified user and movie if it doesn't exist
			//         - update it if it does exist

			return "redirect:/movieratings?user_id=" + rating.getUserId();
	}
}
