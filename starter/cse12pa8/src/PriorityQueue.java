
import java.util.List;

public interface PriorityQueue<K,V> {
    void add(K k, V v);
  
    Entry<K, V> poll();

    Entry<K, V> peek();

    List<Entry<K,V>> toArray();
    
    boolean isEmpty();

}