package application.controllers;

import application.beans.Game;
import application.services.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class GameController {

    String host = "jdbc:mysql://localhost:3306/videogames";
    String uName = "root";
    String uPass = "root";

    @GetMapping("/getAllGames")
    public ArrayList<Game> getAllGames(){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);
        databaseConnector.getAllInfoFromDatabase();

        return databaseConnector.getAllInfoFromDatabase();
    }

    @PostMapping("/game")
    public ArrayList<Game> getGame(String title){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);
        databaseConnector.getAllInfoFromDatabase();

        return databaseConnector.getFromDatabase(title);
    }

    @PostMapping("/createGame")
    public String createGame(Game game){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);
        databaseConnector.getAllInfoFromDatabase();

        return databaseConnector.addEntryToDatabase(game);
    }

    @PutMapping("/game")
    public String updateGame(String title, Game game){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);
        databaseConnector.getAllInfoFromDatabase();

        return databaseConnector.addEntryToDatabase(game);
    }

    @PostMapping("/delete")
    public String removeFromDatabase(String title){
        Game serviceStatusBean;
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);

        return databaseConnector.deleteFromDatabase(title);
    }

}
