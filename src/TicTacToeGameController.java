public class TicTacToeGameController {
    private TicTacToeGame game;

    public TicTacToeGameController(int size, Player[] players, RuleFactory.RuleType type){
        game = TicTacToeGame.createGame(size, players, type);
    }

    public TicTacToeGame getTicTacToeGame(){
        return this.game;
    }

}
