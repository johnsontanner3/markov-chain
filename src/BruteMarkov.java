import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class BruteMarkov implements MarkovInterface<String> {
	private static final int DEFAULT_ORDER = 3;
	private String myText;
	private Random myRandom;
	private int myOrder;
	
	private static String PSEUDO_EOS = "";
	private static long RANDOM_SEED = 5678;
	
	public BruteMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public BruteMarkov() {
		this(DEFAULT_ORDER);
	}
	
	public void setTraining(String text) {
		myText = text;
		
	}
	
	public int size() {
		return myText.length();
	}
	
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);

		String current = myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current); // getFollows is slow AF 
			if (follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size()); // grab random item in your Array 
			
			String nextItem = follows.get(index); // build with random char 
			if (nextItem.equals(PSEUDO_EOS)) {
				//System.out.println("PSEUDO");
				break;
			}
			sb.append(nextItem);
			current = current.substring(1)+ nextItem;
		}
		
		return sb.toString();
		
	}
	// this is what makes this Brutish 
	// create a getFollows that fills map once. Only go through once! 
	// don't search through the entire text each time. Just look up in your map 
	public ArrayList<String> getFollows(String key){ 
		ArrayList<String> follows = new ArrayList<String>();
		
		int pos = 0;  // location where search for key in text starts
		
		while (pos < myText.length()){
			int start = myText.indexOf(key,pos); // scans whole thing for each occurrence of ONE key. Takes forevs
			if (start == -1){
				//System.out.println("didn't find "+key);
				break;
			}
			if (start + key.length() >= myText.length()){
				//System.out.println("found end with "+key);
				follows.add(PSEUDO_EOS);
				break;
			}
			// next line is string equivalent of myText.charAt(start+key.length())
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+1;  // search continues after this occurrence
		}
		return follows;
	}

	@Override
	public int getOrder() {
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);	
	}
	
}
