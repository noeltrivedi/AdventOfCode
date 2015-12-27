import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* Part One:
 * Santa needs help figuring out which strings in his text file are naughty or nice.
 * A nice string is one with all of the following properties:
 * 		It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 * 		It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or 
 * 			aabbccdd (aa, bb, cc, or dd).
 * 		It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the 
 * 		other requirements.
 * 
 * How many strings are nice?
 */
public class DayFive
{
	private final String inputFile = "day5input.txt";
	
	private ArrayList<String> input = new ArrayList<String>();
	
	
	public static void main(String args[])
	{
		new DayFive().solvePartTwo();
	}
	
	DayFive()
	{
		readFile();
	}
	
	private boolean isNice(String word)
	{
		boolean threeVowels = false;
		boolean doubleLetter = false;
		boolean noInvalids = false;
		
		int vowelCount = 0;
		
		for(int i = 0; i < word.length(); i++)
		{
			char c = word.charAt(i);
			if(isVowel(c)) vowelCount++;
			
			if(!doubleLetter && i != word.length()-1 && c == word.charAt(i+1))
				doubleLetter = true;
		}
		
		threeVowels = vowelCount >= 3;
		noInvalids = !(word.contains("ab") || word.contains("cd") || word.contains("pq") || word.contains("xy"));
		
		System.out.println(threeVowels + " " + doubleLetter + " " + noInvalids);
		
		return threeVowels && doubleLetter && noInvalids;
	}
	
	private boolean isVowel(char c)
	{
		return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') ;
	}
	
	private void solvePartOne()
	{
		int niceWordCount = 0;
		for(String s : input)
		{
			if(isNice(s))
				niceWordCount++;
		}
		
		System.out.println(niceWordCount);
	}
	
	private boolean isNewNice(String s)
	{
		boolean hasCharPair = false;
		boolean hasDoubleLetter = false;
		
		for(int i = 0; i < s.length()-1; i++)
		{
			String charPair = "" + s.charAt(i) + s.charAt(i+1);
			if(!hasCharPair && s.substring(i+2).contains(charPair))
				hasCharPair = true;
			
			if(!hasDoubleLetter && i < s.length()-2 && s.charAt(i) == s.charAt(i+2))
				hasDoubleLetter = true;
		}
		
		return hasCharPair && hasDoubleLetter;
	}
	
	private void solvePartTwo()
	{
		int niceWordCount = 0;
		for(String s : input)
		{
			if(isNewNice(s))
				niceWordCount++;
		}
		
		System.out.println(isNewNice("aaaa"));
		
		System.out.println(niceWordCount);
	}
	
	private void readFile()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String line = null;
			while((line = reader.readLine()) != null)
			{
				input.add(line);
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
