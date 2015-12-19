import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class DayTwo
{
	private String inputName = "day2input.txt";
	
	public static void main(String args[])
	{
		new DayTwo().solve();
	}
	
	public void solve()
	{
		readFile();
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
				System.out.println(line);
			}
			br.close();
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
