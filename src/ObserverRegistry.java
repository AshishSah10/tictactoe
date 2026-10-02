import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObserverRegistry{
    static Map<String, IObserver> registry = new HashMap<>();

    public void addToObserverRegistry(IObserver observer){
        registry.put(observer.toString(), observer);
    }

    public void removeFromObserverRegistry(IObserver observer){
        registry.remove(observer.toString());
    }

    public Set<IObserver> getAllObservers(){
        Set<IObserver> observers = new HashSet<>();
        for(Map.Entry<String, IObserver> entry : registry.entrySet()){
            observers.add(entry.getValue());
        }
        return observers;
    }

}
