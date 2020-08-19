package application.services;

import application.beans.Game;

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
                Game game = new Game(rs.getString("Title"), rs.getString("Metadata.Genres"), rs.getString("Metadata.Publishers"), rs.getString("Release.Year"), rs.getInt("Metrics.Review Score"));
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

    public String addEntryToDatabase(Game game){
        try
        {
            Connection con = DriverManager.getConnection(host, uName, uPass);

            String query = " insert into videogames.videogames (title, genre, publisher, release, reviewScore)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString   (1, game.getTitle());
            preparedStmt.setString (2, game.getGenre());
            preparedStmt.setString   (3, game.getPublisher());
            preparedStmt.setString   (4, game.getRelease());
            preparedStmt.setInt   (5, game.getReviewScore());

            preparedStmt.execute();

            con.close();
            return "Entry added to database";
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return "Entry failed";
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
