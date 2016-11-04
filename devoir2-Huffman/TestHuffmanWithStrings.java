/**
 * 
 * Programme pour tester Huffman avec plusieurs chaînes;
 * Les TAs peuvent ajouter d'autres chaînes de caractères pour tester.
 * 
 * Tester Huffman avec des Strings est utile pour tester le codage de Huffman 
 * et pour la visualisation de la sortie de  bits du texte encodé.
 * L'utilisation réelle du codage de Huffman ne produit pas une String avec des
 * bits, car ceci va être inutile (utiliser 2 octets pour stocketr un bit).
 * 
 * L'utilisation de Huffmann avec des fichiers requiert l'utilisation des classes:
 * BitFeedInForFiles implémentant l'interface  BitFeedIn et 
 * BitFeedOutForFiles implémentant l'interface BitFeedOut 
 * 
 * @author Lucia Moura
 */
public class TestHuffmanWithStrings {
	
	public static void main(String[] args) { 
		
		String  codedText, decodedText;
		LetterFrequencies fq;
		
		String [] textsToTest = { // et d'autres chaînes pour d'autres cas de test
				"AACGTAAATAATGAAC",
				"Hey,this is my second test!",
				"I AM SAM, SAM I AM. THAT SAM I AM,THAT SAM I AM, I DO NOT LIKE THAT SAM I AM",
				"SOFIANE MOI SOFIANE MOI",
				"test micro test 1 2 3",
				" . , . ..."
		};
		
		for (String plainText: textsToTest) {
			System.out.println(">>>> Begining Encoding:\n" + plainText); 
			codedText= testStringEncode(plainText); // encode plainText dans codedText
			System.out.println("Bits in coded text:\n"+ codedText); 
			System.out.println("\n>>>> Begining Decoding:\n" + codedText); 
			fq=new LetterFrequencies(plainText);
			decodedText=testStringDecode(codedText,fq); // décode codedText dans decodedText
			System.out.println("Letters in decoded  text:\n"+decodedText);
			if (plainText.equals(decodedText)) // plainText doit crrespondre à decodedText
				System.out.println("RESULT: Correctly encoding-decoding!\n");
			else {
				System.out.println("WRONG: incorrect encoding-decoding");
				System.out.println("Letters in original text:\n"+plainText);
				System.out.println("Letters in decoded  text:\n"+decodedText);
			}
		
		}
		
	}
	
	// Ceci est le codage d'une chaine de caractères en utilisant le codage de Huffman
	// Il teste plusieurs fonctionnalités de HuffmanTree
	// Il retourne une chaine de caractères contenant une séquence de  bits du codage
	
	public static String testStringEncode(String inputText) {
		
		LetterFrequencies lf = new LetterFrequencies(inputText); // calcule les fréquences des lettres
		
		// rempli la liste des fréquences
		HuffmanTree huffTree= new HuffmanTree(lf); // crée l'arbre de Huffman en se basant sur les fréquences des lettres
		
		huffTree.printCodeTable(); //imprime les lettres du codage de  Huffman 
		
		BitFeedOutForString outSeq= new BitFeedOutForString(); 
		// crée outSeq (qui a une interface avec BitFeedOut) qui va recevoir des bits durant le codage
		for (int i=0; i< inputText.length();i++) 
			huffTree.encodeCharacter(inputText.charAt(i),outSeq); // chaque caractère est codé et envoyé à outSeq
		
		huffTree.encodeCharacter(HuffmanTree.EndOfText,outSeq); // le caractère spécial EndOfText est codé et mis à la fin
		
		String codedText = outSeq.output(); // obtient codedText de outSeq
	
        return codedText; // retourne le texte codé
	}
	
	// Ceci est le codage d'une chaine de caractères en utilisant le codage de Huffman
	// Il teste plusieurs fonctionnalités de HuffmanTree
	// Il retourne une chaine de caractères contenant une séquence de  bits du codage


	public static String testStringDecode(String codedText, LetterFrequencies lf) {
			
		// remplie la liste des fréquences
		HuffmanTree huffTree= new HuffmanTree(lf); // utilise les mêmes fréquences pour créer l'arbre (alors l'arbre va être le même)
		 										   
		
		BitFeedInForString seq=new BitFeedInForString(codedText); // crée BitFeedIn pour envoyer les bits encodés un par un
		
		StringBuilder decodedText=new StringBuilder();
		while (seq.hasNext()) {
			 int symbol=huffTree.decodeCharacter(seq); // décode le prochain caractère en utilisant  quelques bits de seq
			 if (symbol == Integer.MAX_VALUE)
				 break;
			 if (symbol!=HuffmanTree.EndOfText)
			     decodedText.append((char) symbol); // collectionne les caractères décodés
			 else break;
		}
		
		return decodedText.toString(); // retourne la chaine décodée
	}
	

}
