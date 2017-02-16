import java.util.*;

public class EfficientWordMarkov implements MarkovInterface<WordGram> {

	private static final int DEFAULT_ORDER = 3;
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	public HashMap<WordGram,ArrayList<String>> myMap;
	private static String PSEUDO_EOS = "";
	private static long RANDOM_SEED = 1234;
	
	public EfficientWordMarkov(int order) {
		myRandom = new Random(RANDOM_SEED);
		myOrder = order;
	}
	
	public EfficientWordMarkov() {
		this(DEFAULT_ORDER);
	}
	
	@Override
	public void setTraining(String text) {
		// declare myText as a list split on whitespace here. 
		String[] textSplit = text.split("\\s+");
		myText = textSplit;
		HashMap<WordGram,ArrayList<String>> map = new HashMap<WordGram, ArrayList<String>>();
		int pos = 0;
		while (pos < myText.length) {
			WordGram key = new WordGram(myText, pos, myOrder);
			if (!map.containsKey(key)) {
				ArrayList<String> empty = new ArrayList<String>();
				map.put(key, empty);
			}
			if (pos + myOrder + 1 >= myText.length) {
				map.get(key).add(PSEUDO_EOS);
				break;
			}
			String nextWord = myText[pos + myOrder]; // this would be a helpful function to write. use @param: String[] 
			map.get(key).add(nextWord);
			pos += 1;
		}
		myMap = map;
	}

	@Override
	public String getRandomText(int length) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);

		WordGram current = new WordGram(myText, index, myOrder); // myText.substring(index, index + myOrder);
		//System.out.printf("first random %d for '%s'\n",index,current);
		String currentCut = current.toString().replaceAll(",", "").replace("{", "").replace("}", " ");
		sb.append(currentCut); // does this have to be private? 
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
			sb.append(nextItem+ " ");
			current = current.shiftAdd(nextItem);  // current.substring(1)+ nextItem;
		}
		
		return sb.toString();
	}

	@Override
	public ArrayList<String> getFollows(WordGram key) {
		// TODO Auto-generated method stub
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

}
