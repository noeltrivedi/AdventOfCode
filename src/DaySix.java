import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DaySix
{
	private final String parsePattern = "(.+)\\s(\\d+),(\\d+).+\\s(\\d+),(\\d+)";
	private final String inputFile = "day6input.txt";
	
	private int[][] lights = new int[1000][1000];
	private Pattern parser;
	
	public static void main(String args[])
	{
		new DaySix().solvePartTwo();
	}
	
	public DaySix()
	{
		parser = Pattern.compile(parsePattern);
	}
	
	private void toggle(Point s, Point e)
	{
		for(int i = s.x; i <= e.x; i++)
		{
			for(int j = s.y; j <= e.y; j++)
			{
				lights[i][j] += 2;
			}
		}
	}
	
	private void turnOn(Point s, Point e)
	{
		for(int i = s.x; i <= e.x; i++)
		{
			for(int j = s.y; j <= e.y; j++)
			{
				lights[i][j] += 1;
			}
		}
	}
	
	private void turnOff(Point s, Point e)
	{
		for(int i = s.x; i <= e.x; i++)
		{
			for(int j = s.y; j <= e.y; j++)
			{
				if(lights[i][j] > 0)
					lights[i][j] -= 1;
			}
		}
	}
	
	private void solvePartTwo()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line = null;
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
				Matcher m = parser.matcher(line);
				if(m.matches())
				{
					String token = m.group(1);
					Point start = new Point(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
					Point end = new Point(Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)));
					switch(token)
					{
					case "toggle":
						toggle(start, end);
						break;
					case "turn on":
						turnOn(start, end);
						break;
					case "turn off":
						turnOff(start, end);
						break;
					}

				}
			}
			
			
			br.close();
			
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		int onCount = 0;
		for(int i = 0; i < 1000; i++)
			for(int j = 0; j <1000; j++)
				onCount += lights[i][j];
		System.out.println(onCount);
	}
}