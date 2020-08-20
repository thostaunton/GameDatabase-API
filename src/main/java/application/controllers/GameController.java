package application.controllers;

import application.beans.Game;
import application.beans.Response;
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
    public Response createGame(@RequestBody Game game){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);

        return databaseConnector.addEntryToDatabase(game);
    }

    @PutMapping("/game")
    public Response updateGame(String title, Game game){
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);
        databaseConnector.getAllInfoFromDatabase();

        return databaseConnector.addEntryToDatabase(game);
    }

    @PostMapping("/delete")
    public String removeFromDatabase(@RequestBody Game game){
        Game serviceStatusBean;
        DatabaseService databaseConnector = new DatabaseService(host, uName, uPass);

        return databaseConnector.deleteFromDatabase(game.getTitle());
    }

}
