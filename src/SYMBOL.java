// An enum is a fixed set of instances known at compile time.
public enum SYMBOL {
    X('X'),
    O('O'),
    EMPTY(' '),

    CUSTOM(' '),

    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    I('I'),
    J('J'),
    K('K'),
    L('L'),
    M('M'),
    N('N'),
    P('P'),
    Q('Q'),
    R('R'),
    S('S'),
    T('T'),
    U('U'),
    V('V'),
    W('W'),
    Y('Y'),
    Z('Z');

    char value;
    public static SYMBOL setValue(char ch){
        CUSTOM.value = ch;
        return CUSTOM;
    }

    private String getValue(){
        return CUSTOM.value+"";
    }

    private SYMBOL(char value){
        this.value = value;
    }

    @Override
    public String toString() {
//        return switch (this) {
//            case X -> "X";
//            case O -> "O";
//            case EMPTY -> "_";
//            default -> getValue()+"";
//        };
        return this.value+"";
    }

}

//public class SYMBOL {
//    private final char value;
//
//    public SYMBOL(char value) {
//        this.value = value;
//    }
//
//    public char getValue() {
//        return value;
//    }
//
//    @Override
//    public String toString(){
//        return this.value+"";
//    }
//}
