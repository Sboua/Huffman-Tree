/**
 *  
 * Interface qui reçoit un flux continu de bits (0 ou 1)
 * inséré par putNext()
 * 
 * Cela donne une flexibilité de communication avec HuffmanTree 
 * en utilisant des chaînes de caractères, des tableaux d'octets ou des fichiers
 * 
 * (utilisée dans le codage de Huffman)
 *
 * @author Lucia Moura
 */


public interface BitFeedOut {

		public void close(); 
		public void putNext(char bit);

}
