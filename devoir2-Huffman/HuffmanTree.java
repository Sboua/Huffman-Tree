import java.util.Arrays;
import java.util.Iterator;

/**
 * HuffmanTree crée et stocke un arbre de  Huffman basé sur les noeuds de Huffman (une classe imbriquée),
 * Elle fournit aussi plusieurs méthodes de codage et de décodage.
 * Elle utilise BitFeedOut qui permet à un flux de bits d'être envoyés continuellement 
 * Pour être utilisée lors du codage.
 * Elle utilise Iterator<Byte> qui permet à un flux de bits d'être lus continuellement
 * Pour être utilisée lors du décodage
 * 
 * @author Lucia Moura
 */

public class HuffmanTree {
	
 public static int EndOfText=((int)'\uffff')+1; //symbole spécial pour indiquer fin de texte
	
 HuffmanNode root=null; // racine de l'arbre de Huffman 
 HuffmanNode[] leafWhereLetterIs;   // tableau indicé par des caractères, stockant la référence 
 				    // au noeud de Huffman (feuille) dans lequel le caractère est stocké

 
 // Constructeur recevant l'information de fréquence qui est utilisée pour appeler BuildTree
 public HuffmanTree (LetterFrequencies letterFreq) {
	 
	 root=BuildTree (letterFreq.getFrequencies(),letterFreq.getLetters());
	 
 }

 // BuildTree construit l'arbre de Huffman
 
 private HuffmanNode BuildTree(int[] frequencies,char[] letters) {
	 
	 
	/******** Étape  2 de Algorithme de Huffman(X) **********************************/
	// nous utilisons une file de priorité pour stocker les fréquences des sous-arbres créés 
	// durant la construction de l'arbre de  Huffman 
	HeapPriorityQueue<HuffmanNode, HuffmanNode> heap = 
			new HeapPriorityQueue<HuffmanNode, HuffmanNode>(frequencies.length+1);
	 
    // initialise le tableau leftWhereLetterIs 
	leafWhereLetterIs =new HuffmanNode[(int)'\uffff'+2]; // besoin d'une taille de 2^16+1 
	for (int i=0; i< (int)'\uffff'+2; i++)
		leafWhereLetterIs[i]=null;
	
	/********* Étapes 3-5 de Algorithme de Huffman(X) **********************************/
	// crée un noeud par lettre comme un seul arbre inséré dans la file de priorité
	for (int i=0; i<frequencies.length; i++) {
		if (frequencies[i]>0) {
			HuffmanNode node= new HuffmanNode( (int)letters[i], frequencies[i],null,null,null);
			leafWhereLetterIs[(int)letters[i]]=node;
			heap.insert(node,node);
		
		}
	}
	// créant un noeud pour le symbole spécial "EndOfText" 
	HuffmanNode specialNode= new HuffmanNode( EndOfText,0,null,null,null);
	leafWhereLetterIs[EndOfText]=specialNode; // last position reserved
	heap.insert(specialNode,specialNode);
	
	while(heap.size()>1){
		Entry<HuffmanNode,HuffmanNode> e1 = heap.removeMin();
		Entry<HuffmanNode,HuffmanNode> e2 = heap.removeMin();
		
		
		HuffmanNode t1 =  e1.getValue();
		HuffmanNode t2 =  e2.getValue();
		int newFreq= e1.getKey().getFrequency()+e2.getKey().getFrequency();
		
		HuffmanNode NewT= new HuffmanNode(000,newFreq,null,t1,t2);
		t1.setParent(NewT);
		t2.setParent(NewT);
		heap.insert(NewT, NewT);
		
		
		
		
		
		
	}
	
	
	/************ Étapes  6-13 de Algorithme de Huffman(X): à implémenter ************/
	
	
	/******       Ici vous implanter le reste de L'ALGORITHME de HUFFMAN(X)  ************/
	
	
	return heap.removeMin().getKey(); /***** doit être changé pour retourner la racine de l'arbre construit par les étapes  6-13 ****/
	
 }
 
 
 private boolean found(HuffmanNode current,int c , boolean b){
	 
	 if(current.isLeaf()){
		 if ((current.getLetter()!=c))
				 return false;
		 else 
		 {
			 if(current.getLetter()==c)
				 return true;
		 }}
		 
		 else {
			 b=found(current.rightChild(),c,b);
			 if (b==true) return true;
			 else 
				 b=found(current.leftChild(),c,b);
			 
		 }
		 
		 return b;
		 
	 }
	 
	 
 
 
 

 
// encodeCharacter code le caractère c en utilisant l'arbre de Huffman 
// retourne son code comme  String de 0s et 1s représentant les  bits
// Dans l'énoncé du devoir si  c='G' cette méthode retourne "011"
 

 

 
 
 private String encodeCharacter(int c){
	 HuffmanNode current = leafWhereLetterIs[c];
	 
	 String s= "";
	 while(current!=root) {
		 
		 if(current.parent.leftChild()==current)
			 {s="0"+s;
			 current=current.parent;
			 }
		 else {
			 s="1"+s;
		 	 current=current.parent;
		 }
	 }		
	 
	 
	 return s;
 }
 
 

 

 
 
 

		 
 private String encodeCharacter2(int c) {
	HuffmanNode current=root;
	 String s="";
	boolean r=false,l=false;
	 while((!current.isLeaf())&&(current.getLetter()!=EndOfText))
	 {
		 if (current.getLetter()==c)
		 	{return s;}
		 
		 else
		 {	r=this.found(current.rightChild(), c, false);
		 	l=this.found(current.leftChild(), c, false);
		 	//System.out.println("RR=="+r);
		 	//System.out.println("L == "+l);
			 { if(r==true) 
			 		{s=s+"1";
			 		current=current.right;
			 		}
			   else{
				   s=s+"0";
				   current=current.left;
			   		}
			 }
			 
			 
		 }
	 }
	 
	 return s;
			
		//return result;
	// return this.find2(c, root, "");
	
 
 }
// Code le caractère  c en utilisant l'arbre de Huffman 
// en envoyant les bits codés à l'argument BitFeedOut bfo
// (ne pas changer cette méthode)
 
 
 public String travers () {
	 return (traverseInOrder2(root,"","")) ; 
 }
	 
 
 
 private String traverseInOrder2(HuffmanNode current, String c,String result) {
	
	
	 if (current.isLeaf()) {
		if (current.getLetter()!=EndOfText) {
		        System.out.println((char)current.getLetter()+":"+c);
		result=result+(char)current.getLetter(); 
		}
		 else {
			 result=result+"endOfText" ; 
			 System.out.println("EndOfText:"+c);
		 }
	 }
	 else { 
		 traverseInOrder2(current.leftChild(),c+"0",result);
		 traverseInOrder2(current.rightChild(),c+"1",result);
		
	 }
		System.out.print(result);
	 return result ; 
 }
 

 public void encodeCharacter (int c, BitFeedOut bfo) {
	 String s=encodeCharacter(c);
	 for (int i=0; i< s.length();i++) bfo.putNext(s.charAt(i));
	
 }
 
 
 // DecodeCharacter reçoit Iterator <Byte> bit qui parcourt une séquence
// de bits de la chaîne codée; cette séquence doit être
// compatible avec l'arbre de Huffman (a été précédemment généré par
// un arbre comme celui-ci.
// Cette méthode va "consommer" des bits jusqu'à ce qu'elle complète le
// décodage d'une lettre qui est ensuite retournée.
// Dans l'exemple de l'énoncé du devoir, si les bits suivants sont 011001 ...
// DecodeCharacter appliquera bit.next () 3 fois jusqu'à ce qu'elle décode
// la première lettre, qui dans ce cas est 'G'
 
public int decodeCharacter(Iterator<Byte> bit) {
	boolean b= false;
	
	HuffmanNode current = root;
	 while(!b){
		 if(current.isLeaf())
			 return current.getLetter();
		 boolean t=false;
		 int i=bit.next();
		 if(i==1)
			 current=current.right;
		 else 
			 current=current.left;
		 }
		 // un arbre vide n'est pas valide lors du décodage
return 0;}
	 
	 /**** Les étapes de cette méthode à mettre en œuvre par les étudiants ****/
     
	 /***
	  * 
	  *
	  * 
	  * Décode la séquence de bits suivants retournée par plusieurs
          * appels à bit.next () jusqu'à ce qu'elle termine le décodage du caractère suivant
          * Note1: la valeur de retour de bit.next () est un octet, mais doit être interprété comme un bit;
          * Il doit être égal à 0 ou 1.
          * Note2: la valeur de retour est un entier (unicode) du caractère; pour un caractère
          * Char c ce qui peut être obtenu par une conversion de caractère: (int) c
	  *  
	  * Rappelez-vous que l'algorithme doit être exécuté en O (L)
          * Où L est la taille du mot de code pour le caractère
	  * 
          *
	  * 
	  */
	
	 
	  /*** cette méthode doit être changée car elle retourne une mauvise sortie (toujours le code dy caractère spécial EndOfText)***/
 
 
 
 
 // méthodes auxiliaires pour l'impression des codes dans l'arbre de Huffman

 void printCodeTable() {
	 System.out.println("**** Huffman Tree: Character Codes ****");
	 if (root!=null) 
		 traverseInOrder(root,""); // utilise le parcours in-ordre pour imprimer les codes
	 else 
		 System.out.println("No character codes: the tree is still empty");
	 System.out.println("***************************************");
	 
 }
 
 // Parcours IN-ordre de l'arbre de Huffman gardant trace des 
 // chemins aux feuilles pour imprimer le mot du code de chaque lettre 
 private void traverseInOrder(HuffmanNode current, String c) {
	 if (current.isLeaf()) {
		if (current.getLetter()!=EndOfText)
		       System.out.println((char)current.getLetter()+":"+c);
		else   System.out.println("EndOfText:"+c);
	 }
	 else { 
		 traverseInOrder(current.leftChild(),c+"0");
		 traverseInOrder(current.rightChild(),c+"1");
	 }
		 
 }
 
 
 //Fourni codage des octets de l'information de fréquence
 // dans le format de 4 octets par lettre
 // 2 premiers octets représentent la lettre et 2 derniers octets représentent la fréquence
 // Ceci est utile pour le  décodage de fichier où les fréquences des lettres doivent
 // être stockées dans un "entête" du fichier encodé
 // (Non utilisée dans la version actuelle du devoir)
 
 
 byte[] freqsToBytes() {
    int b=0;
	byte [] treeBytes= new byte[(int)'\uffff'*4];
    for (int i=0;i<'\uffff';i++) {
		if (leafWhereLetterIs[i]!=null) {
			int freq=leafWhereLetterIs[i].getFrequency();
			char letter=(char)leafWhereLetterIs[i].getLetter();
			treeBytes[b++]= (byte)(((int)letter)/256);
			treeBytes[b++]= (byte)(((int)letter)%256);
			treeBytes[b++]= (byte)(freq/256); 
			treeBytes[b++]= (byte)(freq%256);			
		}
	}
    return Arrays.copyOf(treeBytes, b);
 }
 
 	/**** classe imbriquée qui implémente un noeud de l'arbre de Huffman ****/
    // rien à changer dans cette classe
 	public class HuffmanNode implements Comparable<HuffmanNode> {
		
		int letter; // Si un noeud est une feuille, il stocke une lettre, autrement il stocke  null
	    int frequency; // stocke la somme des fréquences de toutes les feuilles ayant comme racine ce noeud
		private HuffmanNode parent, left, right; // référence au parent, noeuds gauche et droit.
		
		public HuffmanNode() {
			parent=left=right=null;
			frequency=-1;
		}
		
		public HuffmanNode(int letter, int frequency, HuffmanNode parent, HuffmanNode left, HuffmanNode right) {
			this.letter= letter;
			this.frequency=frequency;
			this.parent=parent; 
			this.left=left;
			this.right=right;
		}
		
		
		boolean isLeaf() { return (left==null && right==null);}
		
		// méthodes get
		
		HuffmanNode leftChild() { return left;}
		
		HuffmanNode rightChild() { return right;}
		
		HuffmanNode parent() { return parent;}
		
		int getLetter() {return letter;}
		
		int getFrequency() {return frequency;}

		// méthodes set
		
		void setLeftChild(HuffmanNode leftVal) { left=leftVal;	}
		
		void setRightChild(HuffmanNode rightVal) { right=rightVal;	}
		
		void setParent(HuffmanNode parentVal) { parent=parentVal;	}
		
		void setLetter(char letterVal) { letter = letterVal;}
		
		void setFrequency(int freqVal) { frequency = freqVal; }

		@Override
		public int compareTo(HuffmanNode o) {
			if (this.frequency==o.frequency) {
				return this.letter-o.letter;
			}
			else return this.frequency-o.frequency;
			
		}
		
	}

 
 
}
 
