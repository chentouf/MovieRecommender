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


import java.util.ArrayList;
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

    public Integer getNbMovies(){
        collection= db.getCollection("movies");
        col = db.getCollection("movies");
        DBCursor cursor = collection.find();
        return cursor.count();
    }

    public ArrayList<Movie> getAllMoviesByUserId(Integer idUser){
        ArrayList<Movie> list = new ArrayList<Movie>();
        collection = db.getCollection("users");
        List<Genre> genres = new ArrayList<Genre>();
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
            list.add(getMovieById(idm));
        }
        return list;
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
        ArrayList<Movie> list = new ArrayList<Movie>();
        collection = db.getCollection("movies");
        DBCursor cursor = collection.find();
        while(cursor.hasNext()) {
            DBObject object = cursor.next();
            list.add(new Movie(Integer.parseInt(object.get("_id").toString()),object.get("title").toString(),genres));
        }
        return list;
    }

    public ArrayList<Rating> getRatedMoviesByUserId(Integer idUser){
        ArrayList<Rating> list = new ArrayList<Rating>();
        collection = db.getCollection("users");
        List<Genre> genres = new ArrayList<Genre>();
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
}
