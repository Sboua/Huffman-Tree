
/**
 * La classe LetterFrequencies est pratique pour stocker les lettres et leurs fréquences, 
 * avec la flexibilité de les avoir d'une String ou d'un texte
 *
 * @author Lucia Moura
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LetterFrequencies {
	
	static int NUM_CHARS='\uffff'+1; // il y a 2^16 caractères possibles dans UTF-16
	
	char[] letters;
	int [] freq;
	
	public LetterFrequencies (char[] letters, int[] freq) {
		this.letters=letters.clone();
		this.freq=freq.clone();
	}
	
	public LetterFrequencies(String inputText) {
		
		int [] counter = new int[NUM_CHARS];
		for (int i=0; i<NUM_CHARS; i++) counter[i]=0;
		
		ArrayList<Character> lettersThatShowUp=new ArrayList<Character>();
		
		for (int i=0; i< inputText.length(); i++) {
			
            if (counter[(int)inputText.charAt(i)]==0) lettersThatShowUp.add(inputText.charAt(i));
            
            
			counter[(int)inputText.charAt(i)]++;
		}
		freq=new int[lettersThatShowUp.size()];
		letters=new char[lettersThatShowUp.size()];
		for (int i=0; i<lettersThatShowUp.size();i++) {
			letters[i]=lettersThatShowUp.get(i);
			freq[i]=counter[(int)letters[i]];
		}
	}
	
    public LetterFrequencies(InputStreamReader isr) throws IOException {
		
		int [] counter = new int[NUM_CHARS];
		for (int i=0; i<NUM_CHARS; i++) counter[i]=0;
		
		ArrayList<Character> lettersThatShowUp=new ArrayList<Character>();
		int c;
		
		c = isr.read();
	
		while (c!=-1) {
            if (counter[c]==0) lettersThatShowUp.add((char)c);
			counter[c]++;
			c=isr.read();
		}
		freq=new int[lettersThatShowUp.size()];
		letters=new char[lettersThatShowUp.size()];
		for (int i=0; i<lettersThatShowUp.size();i++) {
			letters[i]=lettersThatShowUp.get(i);
			freq[i]=counter[(int)letters[i]];
		}
	}
	
    
	public int [] getFrequencies() {
		return freq;
	}

	public char [] getLetters() {
		return letters;
	}
}

