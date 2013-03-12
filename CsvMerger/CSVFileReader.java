import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVReader;


public class CSVFileReader
{
	private final String filePath;
	private CSVReader reader;

	public CSVFileReader(String filePath)
	{
		this.filePath = filePath;

		try
		{
			reader = new CSVReader(new FileReader(this.filePath));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public ArrayList<String[]> readAll()
	{
		ArrayList<String[]> rows = null;

		try
		{
			rows = new ArrayList<String[]>(reader.readAll());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return rows;
	}


	public String[] read()
	{
		String[] row = null;

		try
		{
			row = reader.readNext();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return row;
	}


	public void close()
	{
		try
		{
			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}