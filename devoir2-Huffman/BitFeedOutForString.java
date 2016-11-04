/**
 *  
 * Cette implémentation de BitFeedOut utilise StringBuilder pour stocker les bits
 * 
 *
 * @author Lucia Moura
 */

public class BitFeedOutForString implements BitFeedOut {

	StringBuilder bitSeq;
	boolean closed;
	
	public BitFeedOutForString() {
		bitSeq = new StringBuilder("");
		closed=false;
	}
	
	String output() {
		return bitSeq.toString();
	}
	
	public void close() {
		closed=true;
		
	}
	
	public void putNext(char bit) {
		if (closed) {
			closed=false;
			bitSeq = new StringBuilder("");
		}
		bitSeq.append(bit);
	}
	
	
	
}
