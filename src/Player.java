public class Player {
    private final String name;
    private final SYMBOL symbol; // 'X' or 'O'


    public Player(String name, SYMBOL symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public SYMBOL getSymbol() {
        return symbol;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
