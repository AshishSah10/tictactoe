public class Player {
    String name;
    SYMBOL symbol; // 'X' or 'O'


    public Player(String name, SYMBOL symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
