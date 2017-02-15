import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class EfficientMarkov implements MarkovInterface<String> {
	private static final int DEFAULT_ORDER = 3;
	private String myText;
	private Random myRandom;
	private int myOrder;
	public HashMap<String,ArrayList<String>> myMap;
	
	private static String PSEUDO_EOS = "";
	private static long RANDOM_SEED = 1234;
	// need similar constructor as BruteMarkov 
	// Brute has 2 constructors. myRandom and myOrder
	
	public EfficientMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public EfficientMarkov() {
		this(DEFAULT_ORDER);
	}
	
	// @Override
	public void setTraining(String text) {
		// Generate map!!! How long are our grams? this.myOrder
		myText = text;
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		int pos = 0;
		while (pos < myText.length()) {
			String key = text.substring(pos, pos + myOrder);
			if (key.length() < myOrder) {
				break;
			}
			if (!map.containsKey(key) ) {
				ArrayList<String> empty = new ArrayList<String>(); // is this redundant?
				map.put(key, empty); // initialize empty ArrayList. 
			}
			if (pos + myOrder + 1 >= myText.length()) {
				map.get(key).add(PSEUDO_EOS);
				break;
			}
			String nextChar = text.substring(pos + myOrder, pos + myOrder + 1); 
			map.get(key).add(nextChar);
			pos += 1;
		}
		myMap = map;
		// System.out.print(map);
	}
	
	public int size() {
		return myText.length();
	}

	@Override
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		// this.setTraining(myText); // need to call this at some point... 
		int index = myRandom.nextInt(myText.length() - myOrder);
		
		String current = myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		sb.append(current);
		for(int k=0; k < length-myOrder; k++){
			ArrayList<String> follows = getFollows(current); 
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

	@Override
	public ArrayList<String> getFollows(String key) {
		// the only thing we really need to change in this Class. Inherit everything else pretty much.
		// use this to access the map we build in setTraining(). pretty simple stuff 
		// setTraining(myText);
		// ArrayList<String> all = myMap.get(key); // new ArrayList<String>();
		
		// all.addAll(myMap.get(key));
		// return all;
		return myMap.get(key);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return myOrder;
	}

	@Override
	public void setSeed(long seed) {
		RANDOM_SEED = seed;
		myRandom = new Random(RANDOM_SEED);	
	}
	
//	public static void main(String[] args) {
//		String sampleText = "data/clinton-convention.txt";
//		EfficientMarkov test = new EfficientMarkov(2);
//		test.setTraining(sampleText);
//		System.out.print(test.myMap);
//	}

}
