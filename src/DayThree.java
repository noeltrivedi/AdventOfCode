import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/* Part One:
 * Santa is delivering presents to an infinite two-dimensional grid of houses. He begins by 
 * delivering a present to the house at his starting location, and then an elf at the North Pole 
 * calls him via radio and tells him where to move next. Moves are always exactly one house to the 
 * north (^), south (v), east (>), or west (<). After each move, he delivers another present to the 
 * house at his new location.
 * 
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are 
 * a little off, and Santa ends up visiting some houses more than once. 
 * 
 * How many houses receive at least one present?
 * 
 * Part Two:
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to 
 * deliver presents with him.
 * 
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), 
 * then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same 
 * script as the previous year. 
 * 
 * This year, how many houses receive at least one present?
 */
public class DayThree
{
	private String inputFile = "day3input.txt";
	private String input = null;
	private HashSet<Point> santaMap = new HashSet<Point>();
	
	public static void main(String[] args)
	{
		new DayThree().solvePartTwo();
	}
	
	DayThree()
	{
		readFile();		
	}
	
	private void solvePartTwo()
	{
		int santaX = 0;
		int santaY = 0;
		int roboX = 0;
		int roboY = 0;
		boolean isSantaTurn = false;
		
		for(char c : input.toCharArray())
		{
			switch(c)
			{
			case '^':
				if(isSantaTurn) santaY++;
				else roboY++;
				break;
			case 'v':
				if(isSantaTurn) santaY--;
				else roboY--;
				break;
			case '<':
				if(isSantaTurn) santaX--;
				else roboX--;
				break;
			case '>':
				if(isSantaTurn) santaX++;
				else roboX++;
				break;
			}
			
			Point p;
			if(isSantaTurn) p = new Point(santaX, santaY);
			else p = new Point(roboX, roboY);
			santaMap.add(p);
			
			isSantaTurn = !isSantaTurn;
		}
		
		System.out.println(santaMap.size());
	}
	
	private void solvePartOne()
	{
		int x = 0;
		int y = 0;
		
		santaMap.add(new Point(x,y));
		
		for(char c : input.toCharArray())
		{
			switch(c)
			{
			case '^':
				y++;
				break;
			case 'v':
				y--;
				break;
			case '<':
				x--;
				break;
			case '>':
				x++;
				break;
			}
			
			santaMap.add(new Point(x,y));
		}
		
		System.out.println(santaMap.size());
	}
	
	private void readFile()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			//We expect one line
			input = br.readLine();
			
			br.close();
			
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
