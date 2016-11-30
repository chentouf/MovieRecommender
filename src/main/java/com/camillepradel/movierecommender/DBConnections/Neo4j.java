package com.camillepradel.movierecommender.DBConnections;

import com.camillepradel.movierecommender.model.Genre;
import com.camillepradel.movierecommender.model.Movie;
import com.camillepradel.movierecommender.model.Rating;
import org.neo4j.driver.v1.*;

/**
 * Created by a.chentouf on 26/11/2016.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Neo4j {

    private Driver driver;
    private Session session;

    public Neo4j(){

        driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "root" ) );
        session = driver.session();
    }

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> list = new ArrayList<Movie>();
        List<Genre> genres = new ArrayList<Genre>();
        StatementResult result = session.run( "MATCH (m:Movie) RETURN m.title AS title, m.id AS id" );
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(new Movie(record.get( "id" ).asInt(),record.get( "title" ).asString(),genres));
        }
        return list;
    }

    public ArrayList<Movie> getAllMoviesByUserId(Integer idUser){
        ArrayList<Movie> list = new ArrayList<Movie>();
        List<Genre> genres = new ArrayList<Genre>();
        StatementResult result = session.run( "MATCH (u:User{id:"+idUser+"})-[r]->(m:Movie) RETURN m.title AS title, m.id AS id");
        while ( result.hasNext() )
        {
            Record record = result.next();
            list.add(new Movie(record.get( "id" ).asInt(),record.get( "title" ).asString(),genres));
        }
        return list;
    }

    public ArrayList<Rating> getAllMoviesWithRatings(Integer idUser){

        List<Genre> genres = new ArrayList<Genre>();
        ArrayList<Rating> ratings = new ArrayList<Rating>();

        StatementResult result = session.run( "MATCH (u:User{id:"+idUser+"})-[r:RATED]->(m:Movie) RETURN m.title AS title, m.id AS movie_id, r.note AS note, u.id AS user_id" );
        while ( result.hasNext() )
        {
            Record record = result.next();
            ratings.add(new Rating(new Movie(record.get( "movie_id" ).asInt(),record.get( "title" ).asString(),genres), record.get( "user_id" ).asInt(), record.get( "note" ).asInt()));
        }
        System.out.println(ratings);
        return ratings;
    }


    public void close(){
        session.close();
        driver.close();
    }


}
