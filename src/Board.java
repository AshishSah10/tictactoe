public class Board {
    int size; // size of the board, For STANDARD Rule its size is 3 i.e. a 3x3 matrix

    Cell[][] cells; // 2D array of cells representing the board

    private int noOfFilledCells;
    public Board(int size){
        this.size = size;
        cells = new Cell[size][size];
        noOfFilledCells = 0;
    }

    public void initializeBoard(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void displayBoard(){
        System.out.println("---------------------------");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print("["+cells[i][j].symbol+"]"+" ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }


    public boolean isValidCell(int row, int col){
        return this.isValidCell(this.getCell(row, col));
    }

    public boolean isValidCell(Cell cell){
        int row = cell.getRow();
        int col = cell.getCol();
        if(row >= 0 && row < this.size && col >= 0 && col < size){
            return true;
        }
        return false;
    }

    public void incrementFilledCellCount(){
        this.noOfFilledCells++;
    }
    public int getNoOfFilledCells(){
        return this.noOfFilledCells;
    }

    public boolean markCell(Cell cell, SYMBOL symbol){
        if(cell.setValue(symbol)){
            return true;
        }
        return false;
    }
    public Cell getCell(int row, int col){
        if(row >= 0 && row < this.size && col >= 0 && col < this.size){
            return this.cells[row][col];
        }
        return null;
    }

}
