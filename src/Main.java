import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================WELCOME TO TIC-TAC-TOE GAME================");

        // TODO: Dynamically add all Observers
        ObserverRegistry obr = new ObserverRegistry();
        obr.addToObserverRegistry(new ConsoleObserver());

        // TODO: Initialize TicTacToeGameController dynamically
        TicTacToeGameController controller = new TicTacToeGameController(3, new Player[]{new Player("player1",SYMBOL.X), new Player("player2", SYMBOL.O)}, RuleFactory.RuleType.STANDARD);

        // TicTacToeGameController controller = new TicTacToeGameController(6, new Player[]{new Player("player1", SYMBOL.Y), new Player("player2", SYMBOL.O), new Player("player3", SYMBOL.Z)}, RuleFactory.RuleType.STANDARD);

        TicTacToeGame game = controller.getTicTacToeGame();
        if(game == null){
            System.out.println("Wrong game configuration!! please add correct game configuration");
            return;
        }

        // start game
        game.startGame(obr);
        System.out.println("For Undo enter "+game.UNDO);
        while(game.getStatus().equals(TicTacToeGame.GameState.IN_PROGRESS)){
            game.play();
        }
    }
}