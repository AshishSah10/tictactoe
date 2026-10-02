public class ConsoleObserver extends IObserver{
    @Override
    public void update(String message) {
        System.out.println("[Notification]: "+message);
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
