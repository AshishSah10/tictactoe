public class RuleFactory {

    private static Rule instance;
    enum RuleType{
        STANDARD
    }

    public static Rule getInstance(RuleType type){
        if(instance != null){
            return instance;
        }

        switch (type){
            case STANDARD -> {
                instance = new StandardRule();
            }
            default -> {
                return null;
            }
        }

        return instance;
    }
}
