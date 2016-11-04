
/**
 *
 * @author Lachlan
 */
public class Entry<K extends Comparable<K>,V> {
    K key;
    V value;
    
    /**
    * Retourne la clé stockée dans cette entrée.
    * @return the entry's key
    */
    K getKey(){
        return key;
    }

    /**
    * Retourne la valeur stockée dans cette entrée.
    * @return the entry's value
    */
    V getValue(){
        return value;
    }
    
    public Entry(K k, V v){
        key = k;
        value = v;
    }
    
}
