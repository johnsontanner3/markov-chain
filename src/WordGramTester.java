import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class WordGramTester {

	private WordGram[] myGrams;

	@Before
	public void setUp(){
		String str = "aa bb cc aa bb cc aa bb cc aa bb dd ee ff gg hh ii jj";
		String[] array = str.split("\\s+");
		myGrams= new WordGram[array.length-2];
		for(int k=0; k < array.length-2; k++){
			myGrams[k] = new WordGram(array,k,3);
		}
	}

	@Test
	public void testHashEquals(){
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[3].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[0].hashCode(),myGrams[6].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[1].hashCode(),myGrams[4].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[8].hashCode());
		assertEquals("hash fail on equals 0,3",myGrams[2].hashCode(),myGrams[5].hashCode());
	}
	
	@Test
	public void testShiftAdd() { // 
		assertEquals("shift fail on 0,1", myGrams[1].toString(), myGrams[0].shiftAdd("aa").toString());
		assertEquals("shift fail on 1,2", myGrams[2].toString(), myGrams[1].shiftAdd("bb").toString());
		assertEquals("shift fail on 2,3", myGrams[3].toString(), myGrams[2].shiftAdd("cc").toString());
		assertEquals("shift fail on 3,4", myGrams[4].toString(), myGrams[3].shiftAdd("aa").toString());

	}
	
	@Test
	public void testToString() {
		String[] words = {"zero", "one", "two", "three","one", "two", "three" };
		WordGram a = new WordGram(words,0,0); // nothing
		WordGram b = new WordGram(words,1,3); // 123
		WordGram a2 = new WordGram(words,4,3); // 123
		WordGram b2 = new WordGram(words,1,1); // 
		WordGram c = new WordGram(words,4,1); // 
		WordGram d = new WordGram(words,5,2); // 
		WordGram e = new WordGram(words,2,2); // 
	
		assertEquals("string fail three each", b.toString(), a2.toString());
		assertEquals("string fail empty string", a.toString(), "{}");
		assertEquals("string fail one item", b2.toString(), c.toString());
		assertEquals("string fail two each", d.toString(), e.toString());

	}

//	@Test
//	public void testShiftAdd(){
//		assertEquals("shift fail", myGrams[0])
//	}
	
	
	@Test
	public void testEquals(){

		assertEquals("eq fail on 0,3",myGrams[0].equals(myGrams[3]),true);
		assertEquals("eq fail on 0,6",myGrams[0].equals(myGrams[6]),true);
		assertEquals("eq fail on 1,4",myGrams[1].equals(myGrams[4]),true);
		assertEquals("eq fail on 2,5",myGrams[2].equals(myGrams[5]),true);
		assertEquals("eq fail on 2,8",myGrams[2].equals(myGrams[8]),true);
		assertEquals("eq fail on 0,2",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 0,4",myGrams[0].equals(myGrams[2]),false);
		assertEquals("eq fail on 2,3",myGrams[2].equals(myGrams[3]),false);
		assertEquals("eq fail no 2,6",myGrams[2].equals(myGrams[6]),false);
		assertEquals("eq fail no 7,8",myGrams[7].equals(myGrams[8]),false);
	}

	@Test
	public void testHash(){
		Set<Integer> set = new HashSet<Integer>();
		for(WordGram w : myGrams) {
			set.add(w.hashCode());
		}

		assertTrue("hash code test", set.size() > 9);
	}
	
	@Test
	public void testCompare(){
		String[] words = {"apple", "zebra", "mongoose", "hat"};
		WordGram a = new WordGram(words,0,4);
		WordGram b = new WordGram(words,0,4);
		WordGram a2 = new WordGram(words,0,3);
		WordGram b2 = new WordGram(words,2,0);
		
		assertEquals("comp fail self",a.compareTo(a) == 0, true);
		assertEquals("comp fail copy",a.compareTo(b) == 0, true);
		assertEquals("fail sub", a2.compareTo(a) < 0, true);
		assertEquals("fail super",a.compareTo(a2) > 0, true);
		assertEquals("fail empty",b2.compareTo(a2) < 0, true);
	}

}
