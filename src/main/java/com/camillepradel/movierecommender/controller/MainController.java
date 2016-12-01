package com.camillepradel.movierecommender.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.camillepradel.movierecommender.DBConnections.MongoDB;
import com.camillepradel.movierecommender.DBConnections.Neo4j;
import com.camillepradel.movierecommender.model.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
		List<Movie> notRatedMovies = new LinkedList<Movie>();
		neo4j = new Neo4j();
		if(userId != null){
			notRatedMovies = neo4j.getNotRatedMoviesByUserId(userId);
			moviesRatingsNeo4j = neo4j.getAllMoviesWithRatings(userId);
			System.out.println("show the movies rated by : " + userId);
		}

		ModelAndView mv = new ModelAndView("moviesRatingsNeo4j");
		mv.addObject("userId", userId);
		mv.addObject("otherMovies",notRatedMovies);
		mv.addObject("moviesRatingsNeo4j", moviesRatingsNeo4j);
		mv.addObject("moviesRatingsNeo4JSize", moviesRatingsNeo4j.size());
		mv.addObject("otherMoviesSize",notRatedMovies.size());
		neo4j.close();
		return mv;
	}

	@RequestMapping(value = "/moviesRatingsMongoDB", method = RequestMethod.GET)
	public ModelAndView showMoviesRatingsMongoDB(
			@RequestParam(value = "user_id", required = true) Integer userId) {
		System.out.println("GET /movieratings for user " + userId);

		List<Movie> notRatedMoviesMongoDB= new LinkedList<Movie>();
		List<Rating> moviesRatingsMongoDB= new LinkedList<Rating>();
		mongodb = new MongoDB();
		if(userId != null){
			notRatedMoviesMongoDB = mongodb.getNotRatedMoviesByUserId(userId);
			moviesRatingsMongoDB = mongodb.getRatedMoviesByUserId(userId);
			System.out.println("show the movies rated by : " + userId);
		}

		ModelAndView mv = new ModelAndView("moviesRatingsMongoDB");
		mv.addObject("userId", userId);
		mv.addObject("otherMovies",notRatedMoviesMongoDB);
		mv.addObject("moviesRatingsMongoDB", moviesRatingsMongoDB);
		mv.addObject("moviesRatingsMongoDBJSize", moviesRatingsMongoDB.size());
		mv.addObject("otherMoviesSize",notRatedMoviesMongoDB.size());
		return mv;
	}

	//update it if it does exist
	@RequestMapping(value = "/saveOrUpdateRatingNeo4j/{userId}/{movieId}/{score}", method = RequestMethod.GET)
	public String saveOrUpdateRatingNeo4j(@PathVariable("userId") int userId, @PathVariable("movieId")
									 int movieId,@PathVariable("score") int score) {

			neo4j = new Neo4j();
			//update it if it does exist
			neo4j.saveOrUpdateRating(userId,movieId,score);
			neo4j.close();
			return "redirect:/moviesRatingsNeo4j?user_id="+userId;
	}

	//add rating between specified user and movie if it doesn't exist
	@RequestMapping(value = "/createRatingNeo4j/{userId}/{movieId}/{score}", method = RequestMethod.GET)
	public String createRatingNeo4j(@PathVariable("userId") int userId, @PathVariable("movieId")
			int movieId,@PathVariable("score") int score) {

		//add rating between specified user and movie if it doesn't exist
		neo4j = new Neo4j();
		neo4j.createRating(userId,movieId,score);
		neo4j.close();
		return "redirect:/moviesRatingsNeo4j?user_id="+userId;
	}

	//update it if it does exist
	@RequestMapping(value = "/saveOrUpdateRatingMongoDB/{userId}/{movieId}/{score}", method = RequestMethod.GET)
	public String saveOrUpdateRatingMongoDB(@PathVariable("userId") int userId, @PathVariable("movieId")
			int movieId,@PathVariable("score") int score) {

		System.out.println("on est la contr");
		mongodb = new MongoDB();
		//update it if it does exist
		mongodb.saveOrUpdateRating(userId,movieId,score);
		return "redirect:/moviesRatingsMongoDB?user_id="+userId;
	}

	//add rating between specified user and movie if it doesn't exist
	@RequestMapping(value = "/createRatingMongoDB/{userId}/{movieId}/{score}", method = RequestMethod.GET)
	public String createRatingMongoDB(@PathVariable("userId") int userId, @PathVariable("movieId")
			int movieId,@PathVariable("score") int score) {

		//add rating between specified user and movie if it doesn't exist
		mongodb = new MongoDB();
		mongodb.createRating(userId,movieId,score);
		return "redirect:/moviesRatingsMongoDB?user_id="+userId;
	}

}
