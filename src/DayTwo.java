import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DayTwo
{
	private String inputName = "day2input.txt";
	private ArrayList<Integer[]> dimensions = new ArrayList<Integer[]>();
	
	public static void main(String args[])
	{
		new DayTwo().solvePartTwo();
	}
	
	DayTwo()
	{
		//read in the file and fill the dimensions array
		readFile();
	}
	
	public void solvePartTwo()
	{
		int soln = 0;
		
		for(Integer[] input : dimensions)
		{
			soln += findRibbonArea(input);
		}
		
		
		System.out.println(soln);
	}
	
	private int findRibbonArea(Integer[] dim)
	{
		int area = 0;
		int max = Math.max(dim[0], Math.max(dim[1], dim[2]));
		
		//add 2* the lowest perimeter
		area += 2* Math.min(dim[0]+dim[1], Math.min(dim[0]+dim[2], dim[1]+dim[2]));
		
		area += dim[0] * dim[1] * dim[2];
		
		return area;
	}
	
	public void solvePartOne()
	{
		int soln = 0;
		
		for(Integer[] input : dimensions)
		{
			soln += findSurfaceArea(input);
		}
		
		
		System.out.println(soln);
	}
	
	private int findSurfaceArea(Integer[] dim)
	{
		int surfaceArea = 0;
		
		//Find the surface area of each face
		for(int i = 0; i < dim.length; i++)
		{
			for(int j = i; j < dim.length; j++)
			{
				if(i != j)
				{
					//multiply by 2 because there are two faces
					surfaceArea += 2*(dim[i] * dim[j]);
				}
			}
		}
		
		//Find smallest face
		int max = Math.max(dim[0], Math.max(dim[1], dim[2]));
		int smallestFace = dim[0] * dim[1] * dim[2] / max;
		
		surfaceArea += smallestFace;
		
		return surfaceArea;
	}
	
	private void readFile()
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(inputName));
			String line = null;
			while((line = br.readLine()) != null)
			{
				String[] dimStrings = line.split("x"); //Format is axbxc so this will make
														//{'a','b','c'}
				Integer[] dim = new Integer[3];
				for(int i = 0; i < dimStrings.length; i++)
				{
					dim[i] = Integer.parseInt(dimStrings[i]);
				}
				
				dimensions.add(dim);
			}
			br.close();
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
