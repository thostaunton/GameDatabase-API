package application.controllers;

import application.beans.Game;
import application.beans.Message;
import application.services.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
public class GameController {
    
    // saves memory
    private final String host = "jdbc:mysql://localhost:3306/videogames";
    private final String uName = "root";
    private final String uPass = "root";
    
    public void connect() {
     databaseConnector = new DatabaseService(host, uName, uPass);
    }

    @GetMapping("/getAllGames")
    public ArrayList<Game> getAllGames(){
        databaseConnector.getAllInfoFromDatabase();
        return databaseConnector.getAllInfoFromDatabase();
    }

    @PostMapping("/game")
    public ArrayList<Game> getGame(String title){
        databaseConnector.getAllInfoFromDatabase();
        return databaseConnector.getFromDatabase(title);
    }

    @PostMapping("/createGame")
    public Message createGame(@RequestBody Game game){
        return databaseConnector.addEntryToDatabase(game);
    }

    @PostMapping("/delete")
    public String removeFromDatabase(@RequestBody Game game){
        Game serviceStatusBean;
        return databaseConnector.deleteFromDatabase(game.getTitle());
    }

}
