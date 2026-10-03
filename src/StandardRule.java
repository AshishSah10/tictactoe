public class StandardRule implements Rule{
    @Override
    public boolean isValidMove(Board board, Move move) {
        // we only need cell info for our standard rule.
        Cell cell = move.cell;
        return board.isValidCell(cell) && cell.isCellEmpty();
    }

    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize();
        Cell[][] cells = board.getCells();

        // check row-wise
        for(int i = 0; i < size; i++){
            boolean foundWinner = true;
            for(int j = 1; j < size; j++) {
                if(cells[i][j-1].getSymbol() != cells[i][j].getSymbol()){
                    foundWinner = false;
                    break;
                }
            }
            if(foundWinner){
                return cells[i][0].getSymbol() == player.getSymbol();
            }
        }

        // check col-wise
        for(int j = 0; j < size; j++){
            boolean foundWinner = true;
            for(int i = 1; i < size; i++) {
                if(cells[i-1][j].getSymbol() != cells[i][j].getSymbol()){
                    foundWinner = false;
                    break;
                }
            }
            if(foundWinner){
                return cells[0][j].getSymbol() == player.getSymbol();
            }
        }

        // check primary-diagonal
        boolean foundWinner = true;
        for(int i = 0; i < size; i++){
            if(cells[i][i].getSymbol() != player.getSymbol()){
                foundWinner = false;
                break;
            }
        }
        if(foundWinner){
            return true;
        }

        // check anti-diagonal
        foundWinner = true;
        for(int i = 0; i < size; i++){
            if(cells[i][size - 1 - i].getSymbol() != player.getSymbol()) {
                foundWinner = false;
                break;
            }
        }
        if(foundWinner){
            return true;
        }

        return false;
    }

    @Override
    public boolean checkDraw(Board board) {
        return board.getNoOfFilledCells() == board.getSize() * board.getSize();
    }
}
