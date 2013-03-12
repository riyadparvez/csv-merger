import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.charset.Charset;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVReader;


public class CSVFileReader
{
	private final Path filePath;
	private CSVReader reader;

	public CSVFileReader(String filePath)
	{
		this.filePath = FileSystems.getDefault().getPath(filePath);
		Charset charset = Charset.forName("US-ASCII");
		
		try
		{
			BufferedReader bufferdReader = Files.newBufferedReader(this.filePath, charset);
			reader = new CSVReader(bufferdReader);
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