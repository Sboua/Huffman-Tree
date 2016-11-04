/**
 * Implémentation Tableau Tas (Array Heap)  d'une file de priorité
 * @author Lachlan Plant
 */
public class HeapPriorityQueue<K extends Comparable<K>,V> implements PriorityQueue<K ,V> {
    
    private Entry<K,V>[] storage; //Le tas lui même est sous-forme tableau
    private int tail;        //Indice du dernier élément du tas
    
    /**
    * Constructeur par défaut
    */
    public HeapPriorityQueue(){
        this(100);
    }
    
    /**
    * Constructeur de HeapPriorityQueue avec un maximum de stockage pour size éléments
    */
    public HeapPriorityQueue(int size){
        storage = new Entry[size];
        tail = -1;
    }
    
    /****************************************************
     * 
     *             Priority Queue Methods
     * 
     ****************************************************/
    
    /**
    * Retourne le nombre d'éléments dans la file de priorité.
    * O(1)
    * @return number of items
    */
    public int size(){
        return tail +1;
    }

    /**
    * Teste si la file de priorité est vide.
    * O(1)
    * @return true if the priority queue is empty, false otherwise
    */
    public boolean isEmpty(){
        return tail < 0;
    }
    
    /**
    * Insère un couple key-value et retourne l'entrée créée.
    * O(log(n))
    * @param key     la clé de la nouvelle entrée
    * @param value   la valeur de la nouvelle entrée
    * @return the entry storing the new key-value pair
    * @throws IllegalArgumentException if the heap is full
    */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        if(tail == storage.length -1) throw new IllegalArgumentException("Heap Overflow");
        Entry<K,V> e = new Entry<>(key,value);        
        storage[++tail] = e;
        upHeap(tail);
        return e;
    }
    
    /**
    * Retourne (sans enlever) une entrée avec la clé minimale.
    * O(1)
    * @return entry having a minimal key (or null if empty)
    */
    public Entry<K,V> min(){
        if(isEmpty()) return null;
        return storage[0];
    } 
    
    /**
    * Enlève et retourne l'entrée de clé minimale.
    * O(log(n))
    * @return the removed entry (or null if empty)
    */ 
    public Entry<K,V> removeMin(){
        if(isEmpty()) return null;
        Entry<K,V> ret = storage[0];
        if(tail == 0){
            tail = -1;
            storage[0] = null;
            return ret;
        }
        storage[0] = storage[tail];
        storage[tail--] = null;
        downHeap(0);
        return ret;
        
    }  
    
    
    /****************************************************
     * 
     *           Méthodes pour Heap 
     * 
     ****************************************************/
    
    /**
    * Algorithme pour placer élément après l'insertion à la queue.
    * O(log(n))
    */
    private void upHeap(int location){
         if(location == 0) return;
         int parent = parent(location);
         if(storage[parent].key.compareTo(storage[location].key) > 0){
             swap(location,parent);
             upHeap(parent);
         }             
    }
    
    /**
    * Algorithme pour placer un élément après la suppression de la racine et placer l'élément de la queue à la racine.
    * O(log(n))
    */
    private void downHeap(int location){
         int left = (location*2) +1;
         int right = (location*2) +2;
         
         //Both children null or out of bound
         if(left > tail) return;
         //left in right out;
         if(left == tail){
              if(storage[location].key.compareTo(storage[left].key) > 0){
                  swap(location,left);                  
              }
              return;
         }
         int toSwap= (storage[left].key.compareTo(storage[right].key) < 0)? left:right;         
         if(storage[location].key.compareTo(storage[toSwap].key) > 0){
             swap(location,toSwap);
             downHeap(toSwap);
         }             
    }
    
    /**
    * Trouver le parent d'un emplacement donné,
    * Parent de la racine est la racine
    * O(1)
    */
    private int parent(int location){
        return (location-1)/2;
    }
    
   
    /**
    * Permutation sur place de deux éléments, en supposant que les emplacements sont dans un tableau
    * O(1)
    */
    private void swap(int location1, int location2){
        Entry<K,V> temp = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = temp;
    }
    
}
