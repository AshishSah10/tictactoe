public interface Rule {
    boolean isValidMove(Board board, Move move);
    boolean checkWinner(Board board, Player currentPlayer);
    boolean checkDraw(Board board);
}
