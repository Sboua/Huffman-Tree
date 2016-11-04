/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Interface pour TAD file de priorité.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public interface PriorityQueue<K extends Comparable<K>,V> {

  /**
   * Retourne le nombre d'éléments de la file de priorité.
   * @return number of items
   */
  int size();

  /**
   * Teste si la file est vide.
   * @return true if the priority queue is empty, false otherwise
   */
  boolean isEmpty();

  /**
   * Insère un couple  key-value  et retourne l'entrée créée.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  Entry<K,V> insert(K key, V value) throws IllegalArgumentException;

  /**
   * Retourne (mais n'enlève pas)l'entrée avec la clé minimale.
   * @return entry having a minimal key (or null if empty)
   */
  Entry<K,V> min();

  /**
   * Enlève et retourne l'entrée avec la clé minimale.
   * @return the removed entry (or null if empty)
   */
  Entry<K,V> removeMin();
}
