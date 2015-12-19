import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Part One:
 * The elves are running low on wrapping paper, and so they need to submit an order for more. 
 * They have a list of the dimensions (length l, width w, and height h) of each present, and 
 * only want to order exactly as much as they need. Fortunately, every present is a box 
 * (a perfect right rectangular prism), which makes calculating the required wrapping paper 
 * for each gift a little easier: find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l. 
 * The elves also need a little extra paper for each present: the area of the smallest side.
 * 
 * Find the total square feet of wrapping paper they should order.
 * 
 * Part Two:
 * The elves are also running low on ribbon. Ribbon is all the same width, so they only have 
 * to worry about the length they need to order, which they would again like to be exact. The 
 * ribbon required to wrap a present is the shortest distance around its sides, or the smallest 
 * perimeter of any one face. Each present also requires a bow made out of ribbon as well; the 
 * feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the present. 
 * Don't ask how they tie the bow, though; they'll never tell.
 * 
 * Find the total length of ribbon they should order.
 */
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
		area += 2 * (dim[0] + dim[1] + dim[2] - max);
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
