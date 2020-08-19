package application.services;

import application.beans.Game;
import application.beans.Response;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseService {

    private String host;
    private String uName;
    private String uPass;

    public DatabaseService(String host, String uName, String uPass) {
        this.host = host;
        this.uName = uName;
        this.uPass = uPass;
    }

    public ArrayList<Game> getAllInfoFromDatabase(){
        ArrayList<Game> games = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stat = con.createStatement();
            String sql = "select * from videogames";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                Game game = new Game(rs.getString("Title"), rs.getString("Metadata.Genres"), rs.getString("Metadata.Publishers"), rs.getString("Release.Year"), rs.getInt("Metrics.Review Score"));
                games.add(game);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return games;
    }

    public ArrayList<Game> getFromDatabase(String title){
        ArrayList<Game> games = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(host, uName, uPass);

            Statement stat = con.createStatement();
            String sql = "select * from videogames where ID= " + title;
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                Game game = new Game(rs.getString("Title"), rs.getString("Metadata.Genres"), rs.getString("Metadata.Publishers"), rs.getString("Release.Year"), rs.getInt("Metrics.ReviewScore"));
                games.add(game);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return games;
    }

    public String deleteFromDatabase(String title){
        try {
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stat = con.createStatement();
            String sql = "DELETE * from videogames where ID= " + title;
            stat.executeQuery(sql);
            return "Entry deleted from database";
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            return err.getMessage();
        }
    }

    public Response addEntryToDatabase(Game game){
        try
        {
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stat = con.createStatement();

            String update = "insert into videogames.videogames (Title, Genres, Publishers, ReleaseYear, ReviewScore)"
                    + " values ( " + "'" + game.getTitle() + "'" + ", " + "'" + game.getGenre() + "'" + ", " + "'" + game.getPublisher() + "'" + ", " + "'" + game.getRelease() + "'" + ", " + "'" + game.getReviewScore() + "'" + ")";


            stat.executeUpdate(update);

            con.close();
            final Response response = new Response("Entry added to database");
            return response;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        final Response response = new Response("Entry failed");
        return response;
    }

    public String updateEntry(String title, String variableType, String variable){
        try
        {
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stat = con.createStatement();


            String sql = "update videogames set " + variableType + " = " + variable + "where ID= " + title;

            stat.executeQuery(sql);

            con.close();
            return "Entry " + title + "updated";
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return "Entry failed";
    }
}
