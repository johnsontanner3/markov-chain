import java.util.*;

public class WordGram implements Comparable<WordGram> {
	
	private String [] myWords;
	private int myHash;
	
	/*
	 * This is our constructor for WordGram
	 * Store SIZE words from SOURCE starting at START in myWords 
	*/
	public WordGram(String[] source, int start, int size) {
//		if (size == 0) {
//			myWords = new String[1];
//			myWords[0] = " ";
//		}
		myWords = new String[size];
		for (int i=0; i < size; i++) {
			myWords[i] = source[i + start];
		}
	}
	
	public static void main(String[] args) {
		String [] someWords = {"apple", "zebra", "mongoose", "hat"};
		WordGram b2 = new WordGram(someWords, 2, 0);
		WordGram a2 = new WordGram(someWords, 0, 3);
		WordGram a = new WordGram(someWords,0,4);
		WordGram aShift = a.shiftAdd("new");
		System.out.println("a2 "+Arrays.asList(a2));
		System.out.println("a "+Arrays.asList(a));

		int i = a2.compareTo(a);
		System.out.format("The value of a2 comparedTo a is: %d", i);
//		System.out.print("a2 compared to a: " );
//		System.out.println(int a2.compareTo(a));
//		System.out.println(Arrays.asList(a.myWords));
//		System.out.println(Arrays.asList(aShift.myWords));
	}
	
	/*
	 * Use index of each word in myWords to make unique codes 
	 */
	public int hashCode() {
		int hash = 0;
		for (int k=0; k < myWords.length; k++){
			hash = 100*hash + myWords[k].hashCode(); // multiply by index to give unique codes 
		}
		myHash = hash;
		return hash;
	}
	
    public boolean equals(Object other) {
    	if (! (other instanceof WordGram)) return false;
    	
    	WordGram wg = (WordGram) other;
    	for (int k=0; k < myWords.length; k++) {
    		if(!myWords[k].equals(wg.myWords[k])) {
    			return false;
    		}
    	}
    	return true;

    }
    
    // public String nextWord(WordGram o)
	
    public int length() {
    	return myWords.length;
    }
    
    public String toString() {
    	String printable = "{";
    	for (int j=0; j < myWords.length; j++) {
    		if (j == myWords.length -1) {
    			printable += myWords[j];
    		} else {
    			printable += myWords[j]+", ";
    		}
    	}
    	return printable+"}";
    }
    
    public WordGram shiftAdd(String last) { 
    	// start at previous WordGram index 1 and go to last
    	String [] newlist = new String[myWords.length];
    	for (int i=0; i < myWords.length -1; i++) {
    		newlist[i] = myWords[i+1];
    	}

    	
    	newlist[myWords.length -1] = last;
//    	for (String item : newlist) {
//    		System.out.print(item);
//    	}
    	WordGram shifted = new WordGram(newlist, 0, myWords.length); // I don't think myWords is the source we want 
    	return shifted;   	
    }
    
    
    // important to create our own methods because we have custom objects 
	@Override
	public int compareTo(WordGram o) {
		if (myWords.length == 0 && o.myWords.length > 0) {
			return -1;
		}
		if (o.myWords.length == 0 && myWords.length > 0) {
			return 1;
		}
		if (o.myWords.length != myWords.length) {
			int themin = Math.min(o.myWords.length, myWords.length);
			for (int k=0; k < themin; k++) {
				if (myWords[k].equals(o.myWords[k])) {
					continue;
				} return myWords[k].compareTo(o.myWords[k]);
			}
		}
		
		if (o.myWords.length == (myWords.length)) {
			for (int i= 0; i < o.myWords.length; i++) {
				if (myWords[i].equals(o.myWords[i])) {
					continue;
				} else {
					return myWords[i].compareTo(o.myWords[i]);
				}
		}
		
//		for (int i= 0; i < o.myWords.length; i++) {
//			if (myWords[i].equals(o.myWords[i])) {
//				continue;
//			} else {
//				return myWords[i].compareTo(o.myWords[i]);
//			}
		}
		return 0;
		// TODO Auto-generated method stub
//		if (Arrays.toString(o.myWords).equals("") && !Arrays.toString(myWords).equals("")) {
//			return -1;
//		}
//		System.out.println(Arrays.toString(this.myWords));
//		System.out.println(Arrays.toString(o.myWords));
		//String current = Arrays.toString(this.myWords);
		// String currentSlice = current.substring(0, -1);
		// String oth = Arrays.toString(o.myWords);
//		String currentSquish = current.replace(",", "");
////		String currentSquish2 = currentSquish.substring(1, -1);
//
//
//		String othSquish = oth.replace(",", "");

//		System.out.println("this is current: " + currentSquish2);
//		
//		System.out.println("this is oth: " + othSquish);

		// String othSlice = oth.substring(0, -1);
		//return oth.compareTo(current);
		// return Arrays.toString(this.myWords).substring(1, -1).compareTo(Arrays.toString(o.myWords).substring(1, -1));
		// return this.compareTo(o); can't do this because you have like hash variables and stuff 
	}

}
