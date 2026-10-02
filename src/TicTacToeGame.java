import java.util.*;

public class TicTacToeGame {
    Board board; // At least size 3x3 size board needed
    Player[] players; // at least 2 players needed
    private Player currentPlayer;
    private Stack<Move> moves;

    private GameState status;
    enum GameState {
        BEFORE_START,
        IN_PROGRESS,
        DRAW,
        WIN
    }

    private Deque<Player> playerTurn; // which player turn is now based on index players

    private Rule rule;

    private Set<IObserver> observers;

    public static int UNDO = 999;

    private TicTacToeGame(int boardSize, Player[] players, RuleFactory.RuleType type) {
        this.board = new Board(boardSize);
        this.players = players;
        this.rule = RuleFactory.getInstance(type);
        this.playerTurn = new ArrayDeque<>();
        this.moves = new Stack<>();
        this.status = GameState.BEFORE_START;
    }

    public static TicTacToeGame createGame(int size, Player[] players, RuleFactory.RuleType type){
        if(!validateGameConfig(size, players, type)){
            return null;
        }

        TicTacToeGame game = new TicTacToeGame(size, players, type);
        game.initializePlayersTurn();
        game.initializeBoard();

        game.observers = new HashSet<>();

        return game;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    public GameState getStatus(){
        return this.status;
    }

    public void addObserver(IObserver observer){
        this.observers.add(observer);
    }
    public void removeObserver(IObserver observer){
        this.observers.remove(observer);
    }

    public void notify(String message){
        for(IObserver observer : this.observers){
            observer.update(message);
        }
    }

    public void play(){
        System.out.println("Current turn is of player: "+this.currentPlayer.name+", whose symbol is: "+this.currentPlayer.symbol);
        System.out.println("Input integer row and col: ");

        Scanner sc = new Scanner(System.in);
        boolean canDoUndo = isUndoPossible();
        int row = sc.nextInt();
        if(checkUserWantsToDoUndo(row)){
            doUndo(canDoUndo);
            return;
        }

        int col = sc.nextInt();
        if(checkUserWantsToDoUndo(col)){
            doUndo(canDoUndo);
            return;
        }

        this.playNextMove(row, col);

        this.board.displayBoard();
    }

    private void doUndo(boolean canDoUndo) {
        if(canDoUndo){
            doUndo();
        }
        else{
            notify("Cannot perform the Undo operation");
        }
        this.board.displayBoard();
    }

    private boolean checkUserWantsToDoUndo(int no){
        if(no == UNDO){
            return true;
        }
        return false;
    }

    private boolean isUndoPossible() {
        if(this.moves.isEmpty()){
            return false;
        }
        return true;
    }

    private void doUndo(){
        Move move = this.popPreviousMove();
        Cell cell = move.cell;
        playerTurn.addFirst(this.currentPlayer);
        cell.setCellEmpty();
        this.currentPlayer = move.player;
        this.notify("Successfully Undo the last move from cell: "+cell);
    }

    private static boolean validateGameConfig(int size, Player[] players, RuleFactory.RuleType type) {
        if(players == null || players.length < 2 || size < 3){
            System.out.println("Board size should be at least 3 and at least 2 players needed to play the game");
            return false;
        }
        if(type.equals(RuleFactory.RuleType.STANDARD) && players.length != 2 && size != 3){
            System.out.println("For STANDARD game only 2 players needed and board should be of size 3x3");
            return false;
        }
        return true;
    }

    public void startGame(ObserverRegistry observerRegistry){
        this.board.displayBoard();
        this.status = GameState.IN_PROGRESS;
        this.currentPlayer = getCurrentPlayerAndUpdateTurn();
        this.observers = observerRegistry.getAllObservers();
    }

    private void initializeBoard(){
        this.board.initializeBoard();
    }

    private void initializePlayersTurn(){
        for(int i = 0; i < this.players.length; i++){
            this.playerTurn.addLast(this.players[i]);
        }
    }

    private void playNextMove(int row, int col){
        Player currentPlayer = getCurrentPlayer();
        Cell cell = this.board.getCell(row, col);
        if(currentPlayer == null || cell == null){
            return;
        }

        Move move = new Move(cell, currentPlayer);

        if(!this.rule.isValidMove(this.board, move)){
//            System.out.println("Entered row and col are not valid, please input again");
            this.notify("Entered row and col are not valid, please input again");
            return;
        }

        if(!board.markCell(cell, currentPlayer.symbol)){
            return;
        }

        this.pushNextMove(move);
        this.notify("Player "+currentPlayer+" mark "+currentPlayer.symbol+" at cell["+row+"]["+col+"]");
        this.board.incrementFilledCellCount();
        updateGameStatus();
    }

    private void updateGameStatus(){
        if(this.rule.checkWinner(this.board, this.currentPlayer)){
           // we have a winner
            this.status = GameState.WIN;
            // System.out.println("Winner of the Game is: "+this.currentPlayer.name+" and Symbol is "+this.currentPlayer.symbol);
            this.notify("Winner of the Game is: "+this.currentPlayer.name+" and Symbol is "+this.currentPlayer.symbol);
            return;
        }

        // If we don't have winner, check for draw
        if(this.rule.checkDraw(this.board)){
            // System.out.println("The Game is Draw");
            this.notify("The Game is Draw");
            this.status = GameState.DRAW;;
            return;
        }

        // No winner, No Draw, Game must go on.....
        this.currentPlayer = getCurrentPlayerAndUpdateTurn();
        this.status = GameState.IN_PROGRESS;
    }

    private void pushNextMove(Move move){
        moves.push(move);
    }

    private Move popPreviousMove(){
        if(moves.isEmpty()){
            // System.out.println("You are at starting of the game");
            this.notify("You are at starting of the game");
            return null;
        }

        return moves.pop();
    }

    private Player getCurrentPlayerAndUpdateTurn(){
        if(playerTurn.isEmpty()){
            return null;
        }
        this.currentPlayer = this.playerTurn.removeFirst();
        this.playerTurn.addLast(this.currentPlayer);
        return this.currentPlayer;
    }
}
