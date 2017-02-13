
public class WordGram implements Comparable<WordGram> {
	
	private String [] myWords;
	private int myIndex;
	private int myStart;
	
	
	// This is our constructor for WordGram
	public WordGram(String[] words, int index, int start) {
		myWords = words;
		myIndex = index;
		myStart = start;
	}
	
	public int hashCode() {
		return 0;
	}
	
    public boolean equals(Object other) {
    	return true;
    }
    
    // public String nextWord(WordGram o)
	
    public int length() {
    	return myWords.length;
    }
    
    public String toString() {
    	return "";
    }
    
    public WordGram shiftAdd(String last) {
    	return null;
    }
    
	@Override
	public int compareTo(WordGram o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//private String[] myWords;
	//    
//	    public boolean equals(Object o){
//	        if (obj == this) 
//	            return true;
	//
//	        if (obj == null || obj.getClass() !=  this.getClass()) 
//	            return false;
//	        
//	        WordGram other = (WordGram) o;
//	        for(int k = 0; k < myWords.length; k++) 
//	            if (!myWords[k].equals(other.myWords[k]))
//	                return false;
//	        return true;
//	    }
	//}

}
