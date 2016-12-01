package com.camillepradel.movierecommender.DBConnections;

/**
 * Created by a.chentouf on 26/11/2016.
 */
import com.camillepradel.movierecommender.model.Genre;
import com.camillepradel.movierecommender.model.Movie;
import com.camillepradel.movierecommender.model.Rating;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.bson.BSONObject;
import scala.util.parsing.json.JSONObject;


import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MongoDB {

    public MongoClient mongo;
    public DB db;
    public DBCollection collection;
    public DBCollection col;




    public MongoDB(){
        mongo = new MongoClient( "localhost" , 27017 );
        db = mongo.getDB("MovieLens");
    }

    public ArrayList<Movie> getAllMoviesByUserId(Integer idUser){
        collection = db.getCollection("users");
        ArrayList<Movie> listMoviesUser = new ArrayList<Movie>();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", idUser);
        DBObject object = collection.findOne(query);
        BasicDBList lights = (BasicDBList) object.get("movies");
        List<BSONObject> res = new ArrayList<BSONObject>();
        for (int i = 0; i < lights.size(); i++) {
            res.add((BSONObject) lights.get(i));
        }
        for (int i = 0; i < res.size(); i++) {
            BSONObject b = (BSONObject) lights.get(i);
            Double id = Double.parseDouble(b.get("movieid").toString());
            int idm = id.intValue();
            listMoviesUser.add(getMovieById(idm));
        }
        return listMoviesUser;
    }

    public Movie getMovieById(Integer id)
    {
        collection = db.getCollection("movies");
        List<Genre> genres = new ArrayList<Genre>();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        DBObject object = collection.findOne(query);
        return new Movie(Integer.parseInt(object.get("_id").toString()),object.get("title").toString(),genres);

    }

    public ArrayList<Movie> getAllMovies(){
        List<Genre> genres = new ArrayList<Genre>();
        ArrayList<Movie> listAllMovies = new ArrayList<Movie>();
        collection = db.getCollection("movies");
        DBCursor cursor = collection.find().sort(new BasicDBObject("_id",1));
        while(cursor.hasNext()) {
            DBObject object = cursor.next();
            listAllMovies.add(new Movie(Integer.parseInt(object.get("_id").toString()),object.get("title").toString(),genres));
        }
        return listAllMovies;
    }

    public ArrayList<Rating> getRatedMoviesByUserId(Integer idUser){
        ArrayList<Rating> list = new ArrayList<Rating>();
        collection = db.getCollection("users");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", idUser);
        DBObject object = collection.findOne(query);
        BasicDBList lights = (BasicDBList) object.get("movies");
        List<BSONObject> res = new ArrayList<BSONObject>();
        for (int i = 0; i < lights.size(); i++) {
            res.add((BSONObject) lights.get(i));
        }
        for (int i = 0; i < res.size(); i++) {
            BSONObject b = (BSONObject) lights.get(i);
            Double id = Double.parseDouble(b.get("movieid").toString());
            Double note = Double.parseDouble(b.get("rating").toString());
            int idm = id.intValue();
            int notem = note.intValue();
            Movie m = getMovieById(idm);
            list.add(new Rating(m, idUser, notem));
        }
        return list;
    }

    public ArrayList<Movie> getNotRatedMoviesByUserId(Integer userId)
    {
        ArrayList<Movie> listNotRatedlMovies = new ArrayList<Movie>();
        ArrayList<Movie> allMovies = getAllMovies();
        ArrayList<Movie> listMoviesUser = getAllMoviesByUserId(userId);
        List<Integer> l = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        for (Movie i : allMovies)
        {
            l.add((int) i.getId());
        }
        for (Movie i : listMoviesUser)
        {
            l2.add((int) i.getId());
        }
        l.removeAll(l2);
        for (int i : l)
        {
            listNotRatedlMovies.add(getMovieById(i));
        }
        return listNotRatedlMovies;
    }

    public void createRating(int userId, int movieId, int score)
    {
        collection = db.getCollection("users");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", userId);
        BasicDBObject movies = new BasicDBObject();
        BasicDBObject set = new BasicDBObject("$addToSet", new BasicDBObject("movies",movies));

        movies.put("movieid", movieId);
        movies.put("rating", score);
        movies.put("date",Math.round(new Date().getTime()/1000));
        collection.update(query,set);

        getRatedMoviesByUserId(userId);

    }

    public void saveOrUpdateRating(int userId, int movieId, int score)
    {
        collection = db.getCollection("users");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", userId);
        BasicDBObject movies = new BasicDBObject();
        BasicDBObject pull = new BasicDBObject("$pull", new BasicDBObject("movies",movies));
        movies.put("movieid", movieId);
        collection.update(query,pull);

        BasicDBObject set = new BasicDBObject("$addToSet", new BasicDBObject("movies",movies));

        movies.put("movieid", movieId);
        movies.put("rating", score);
        movies.put("date",Math.round(new Date().getTime()/1000));
        collection.update(query,set);

        getRatedMoviesByUserId(userId);
    }
}
