import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;


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
