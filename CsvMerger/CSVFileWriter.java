import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import au.com.bytecode.opencsv.CSVWriter;


public class CSVFileWriter
{
	private final String filePath;
	private CSVWriter writer;


	public CSVFileWriter(String filePath)
	{
		this.filePath = filePath;

		try
		{
			writer = new CSVWriter(new FileWriter(this.filePath));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}


	public void write(String[] row)
	{
		writer.writeNext(row);
	}


	public void close()
	{
		try
		{
			writer.flush();
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}