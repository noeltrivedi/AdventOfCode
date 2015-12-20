import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/* Part One:
 * Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts 
 * for all the economically forward-thinking little girls and boys.
 * 
 * To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least 
 * five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) 
 * followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive 
 * number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
 * 
 * Your puzzle input is 'yzbqklnj'
 * 
 * Part Two:
 * Same thing but with 6 zeroes
 */
public class DayFour
{
	private String input = "yzbqklnj";
	
	public static void main(String args[])
	{
		new DayFour().solvePartTwo();
	}
	
	private void solvePartTwo()
	{
		System.out.println(findHashWithPrefix("000000"));
	}
	
	private void solvePartOne()
	{
		System.out.println(findHashWithPrefix("00000"));
	}
	
	private int findHashWithPrefix(String prefix)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			HexBinaryAdapter hexConverter = new HexBinaryAdapter();
			int offset = 0;
			
			while(true) //use a while true because the break condition is long and complicated
			{
				++offset;
				String toDigest = input + offset;
				String hex = hexConverter.marshal(md.digest(toDigest.getBytes()));
				
				if(hex.startsWith(prefix)) break;
			}
			

			return offset;
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return -1; //error
	}
}
