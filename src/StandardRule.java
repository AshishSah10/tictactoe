public class StandardRule implements Rule{
    @Override
    public boolean isValidMove(Board board, Move move) {
        // we only need cell info for our standard rule.
        Cell cell = move.cell;
        if(!board.isValidCell(cell) || !cell.isCellEmpty() ) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkWinner(Board board, Player player) {
        int size = board.size;
        Cell[][] cells = board.cells;

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
                return cells[i][0].getSymbol() == player.symbol;
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
                return cells[0][j].getSymbol() == player.symbol;
            }
        }

        // check primary-diagonal
        boolean foundWinner = true;
        for(int i = 1; i < size; i++){
            for(int j = 1; j < size; j++) {
                if(i == j && cells[i-1][j-1].getSymbol() != cells[i][j].getSymbol()){
                    foundWinner = false;
                    break;
                }
            }
        }
        if(foundWinner){
            return cells[1][1].getSymbol() == player.symbol;
        }

        // check anti-diagonal
        foundWinner = true;
        SYMBOL prevSymbol = SYMBOL.EMPTY;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if(i + j == size - 1 ){
                    if(prevSymbol != SYMBOL.EMPTY && prevSymbol != cells[i][j].getSymbol()) {
                        foundWinner = false;
                        break;
                    }
                    prevSymbol = cells[i][j].getSymbol();
                }
            }
        }
        if(foundWinner){
            return cells[1][1].getSymbol() == player.symbol;
        }

        return false;
    }

    @Override
    public boolean checkDraw(Board board) {
        return board.getNoOfFilledCells() == board.size * board.size;
    }
}
