public class Cell {
    private int row;
    private int col;
    SYMBOL symbol; // 'X', 'O', or ' ' for empty


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.symbol = SYMBOL.EMPTY; // empty cell
    }

    public SYMBOL getSymbol(){
        return this.symbol;
    }

    public boolean setValue(SYMBOL symbol){
        if(!this.isCellEmpty()){
            return false;
        }
        this.symbol = symbol;
        return true;
    }

    public boolean isCellEmpty(){
        return this.symbol == SYMBOL.EMPTY;
    }

    public void setCellEmpty(){
        this.symbol =  SYMBOL.EMPTY;
    }

    @Override
    public String toString(){
        return "["+this.row+"]["+this.col+"]="+this.symbol;
    }



}
